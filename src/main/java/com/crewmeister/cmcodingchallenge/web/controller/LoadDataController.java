package com.crewmeister.cmcodingchallenge.web.controller;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyDto;
import com.crewmeister.cmcodingchallenge.domain.service.CurrencyService;
import com.crewmeister.cmcodingchallenge.domain.service.ExchangeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class LoadDataController {

    private static Logger LOGGER = LogManager.getLogger(LoadDataController.class);

    private CurrencyService currencyService;

    private ExchangeService exchangeService;


    public LoadDataController(CurrencyService currencyService, ExchangeService exchangeService) {
        this.currencyService = currencyService;
        this.exchangeService = exchangeService;
    }

    @GetMapping("/loadData")
    public ResponseEntity loadCurrencies() {
        LOGGER.info("CurrencyController::loadCurrencies");
        currencyService.loadCurrencies();
        exchangeService.loadRates();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}