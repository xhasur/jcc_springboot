package com.crewmeister.cmcodingchallenge.web.controller;

import com.crewmeister.cmcodingchallenge.domain.service.CurrencyService;
import com.crewmeister.cmcodingchallenge.domain.service.ExchangeService;
import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class CurrencyController {

    private static Logger LOGGER = LogManager.getLogger(CurrencyController.class);

    private CurrencyService currencyService;

    private ExchangeService exchangeService;

    public CurrencyController(CurrencyService currencyService, ExchangeService exchangeService) {
        this.currencyService = currencyService;
        this.exchangeService = exchangeService;
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<CurrencyEntity>> getCurrencies() {
        LOGGER.info("CurrencyController::getCurrencies");
        return new ResponseEntity<>(currencyService.getAllCurrencies(), HttpStatus.OK);
    }

    @GetMapping("/ratescurrencies")
    public ResponseEntity<List<ExchangeRate>> getAllCurrenciesByCurrency(@RequestParam(name = "currency")String currency) {
        LOGGER.info("CurrencyController::getCurrencies");
        return new ResponseEntity<>(exchangeService.getAllCurrenciesByCurrency(currency), HttpStatus.OK);
    }
}
