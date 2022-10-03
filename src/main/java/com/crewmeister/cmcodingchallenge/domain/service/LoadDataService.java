package com.crewmeister.cmcodingchallenge.domain.service;

import com.crewmeister.cmcodingchallenge.CmCodingChallengeApplication;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
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


    public  Set<ExchangeRate> readExchangeFromCsv(String url ,Currency currency) {
        AtomicInteger index = new AtomicInteger();
        BufferedReader br = null;
        Set<ExchangeRate> exchanges = new HashSet<>();
        try {

            br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            return br.lines()
                    .map(k  -> {
                        index.set(index.get() + 1);
                        String[] value = k.split(",");
                        if(index.get() >= 10){
                            ExchangeRate exchange = new ExchangeRate( currency, value[1], value[0], value.length>2 ? value[2] : "");
                            exchanges.add(exchange);
                        }
                        return exchanges;
                    }).collect(Collectors.toList()).get(0);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public  Currency getCurrency(String currency) {
        try {
            return  Currency.getInstance(currency);
        } catch (IllegalArgumentException e) {
            LOGGER.error("error creating currency value" + e);
        }
        return null;
    }
}