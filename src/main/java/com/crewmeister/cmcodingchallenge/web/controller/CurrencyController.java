package com.crewmeister.cmcodingchallenge.web.controller;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyDto;
import com.crewmeister.cmcodingchallenge.domain.service.CurrencyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
public class CurrencyController {

    private static Logger LOGGER = LogManager.getLogger(CurrencyController.class);

    private CurrencyService currencyService;


    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies")
    @ApiOperation("Get all currencies")
    @ApiResponse(code =200, message = "OK")
    public ResponseEntity<List<CurrencyDto>> getCurrencies() {
        LOGGER.info("CurrencyController::getCurrencies");
        return new ResponseEntity<>(currencyService.getAllCurrencies(), HttpStatus.OK);
    }

}
