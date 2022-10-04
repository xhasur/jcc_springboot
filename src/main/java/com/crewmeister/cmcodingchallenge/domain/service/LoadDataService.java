package com.crewmeister.cmcodingchallenge.domain.service;

import com.crewmeister.cmcodingchallenge.CmCodingChallengeApplication;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRateEntity;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class LoadDataService {
    private static Logger LOGGER = LogManager.getLogger(CmCodingChallengeApplication.class);


    public  Document getBankdocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOGGER.error("Error loading page");
            throw new RuntimeException(e);
        }
        return doc;
    }


    public  Set<ExchangeRateEntity> readExchangeFromCsv(String url , Currency currency) {
        AtomicInteger index = new AtomicInteger();
        BufferedReader br = null;
        Set<ExchangeRateEntity> exchanges = new HashSet<>();
        try {
            br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            return br.lines()
                    .map(k  -> getExchangeRateEntities(currency, index, exchanges, k))
                    .collect(Collectors.toList()).get(0);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Set<ExchangeRateEntity> getExchangeRateEntities(Currency currency, AtomicInteger index, Set<ExchangeRateEntity> exchanges, String k) {
        exchanges.remove(exchanges.size() - 1);
        index.set(index.get() + 1);
        String[] value = k.split(",");
        if (index.get() >= 10) {
            try {
                BigDecimal rate = value[1].equals(".") ? new BigDecimal(0) : BigDecimal.valueOf(Double.valueOf(value[1]));
                exchanges.add(new ExchangeRateEntity(currency, rate, getLocalDate(value[0]), value.length > 2 ? value[2] : ""));
            } catch (NumberFormatException ex) {
                exchanges.add(new ExchangeRateEntity(currency, new BigDecimal(0), getLocalDate(value[0]), value.length > 2 ? value[2] : ""));
            }
        }
        return exchanges;
    }

    public  Currency getCurrency(String currency) {
        try {
            return Currency.getInstance(currency);
        } catch (IllegalArgumentException e) {
            LOGGER.error("error creating currency value" + currency);
        }
        return null;
    }

    public LocalDate getLocalDate(String date) {
        try {
            if (date.isEmpty() || date.isBlank()) return null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            LOGGER.error("error Casting object to LocalDate  " + e.getMessage());
        }
        return null;
    }
}