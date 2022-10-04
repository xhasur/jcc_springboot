package com.crewmeister.cmcodingchallenge.domain.service;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyDto;
import com.crewmeister.cmcodingchallenge.domain.repository.ICurrencyRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CurrencyService {

    private static Logger LOGGER = LogManager.getLogger(CurrencyService.class);

    @Value("${bundesbank.currency.exchange.service.url}")
    private String url;

    private ICurrencyRepository currencyRepository;

    private LoadDataService loadDataService;

    public CurrencyService(ICurrencyRepository currencyRepository, LoadDataService loadDataService) {
        this.currencyRepository = currencyRepository;
        this.loadDataService = loadDataService;
    }

    @Transactional
    public void loadCurrencies() {
        LOGGER.info("loadCurrencies");
        Set<CurrencyDto> currencies = new HashSet<>();
        Document doc = this.loadDataService.getBankDocument(url);
        doc.select("table tbody tr")
                .parallelStream()
                //.peek(LOGGER::info)
                .forEach(( element -> {
                        Elements tds = element.select("td");
                        if(tds.isEmpty()) return;
                        Element dtdDescription = tds.get(1);
                        String currencyName = dtdDescription.text().split("=")[1].split("... /")[0].trim();
                        Currency currency = Currency.getInstance(currencyName);
                        currencies.add(new CurrencyDto(currency));
        }));
        if(!currencies.isEmpty()){
            this.currencyRepository.saveAll(currencies);
        }
    }

    public List<CurrencyDto> getAllCurrencies() {
        return this.currencyRepository.getCurrencies();
    }



}
