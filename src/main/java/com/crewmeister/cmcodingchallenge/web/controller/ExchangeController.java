package com.crewmeister.cmcodingchallenge.web.controller;

import com.crewmeister.cmcodingchallenge.domain.dto.ExchangeDto;
import com.crewmeister.cmcodingchallenge.domain.service.ExchangeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class ExchangeController {

    private static Logger LOGGER = LogManager.getLogger(ExchangeController.class);


    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }


    @GetMapping("/currencyexchanges")
    @ApiOperation("Get all currencies exchanges")
    @ApiResponse(code =200, message = "OK")
    public ResponseEntity<List<ExchangeDto>> getAllCurrenciesExchanges() {
        LOGGER.info("ExchangeController::getAllCurrencies");
        return new ResponseEntity<>(exchangeService.getAllExchangesRates(), HttpStatus.OK);
    }

    @GetMapping("/currencyexchanges/pageable")
    @ApiOperation("Get all currencies exchanges pageable")
    @ApiResponse(code =200, message = "OK")
    public ResponseEntity<List<ExchangeDto>> getAllCurrenciesExchangesPageable(@RequestParam int page,
                                                                               @RequestParam int size) {
        LOGGER.info("ExchangeController::getAllCurrencies");

        return new ResponseEntity<>(exchangeService.getAllExchangesRates(page, size), HttpStatus.OK);
    }

    @GetMapping("/exchangerates/{date}")
    @ApiOperation("Get all currencies exchanges by date")
    @ApiResponse(code =200, message = "OK")
    public ResponseEntity<List<ExchangeDto>> getExchangeRatesForDate(@ApiParam(value = "Date of exchange currency", example = "1999-01-04")
                                                                     @PathVariable("date")
                                                                     @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                     @Validated LocalDate date) {
        LOGGER.info("ExchangeController::getExchangeRatesForDate");
        return new ResponseEntity<>(exchangeService.getExchangeRatesForDate(date), HttpStatus.OK);

    }


    @GetMapping("/euroconverter/{date}")
    @ApiOperation("Convert currency exchange to EUR by date")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<ExchangeDto>> getEuroConverter(@ApiParam(value = "Date of exchange currency", example = "1999-01-04")
                                                              @PathVariable("date")
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                              @Validated LocalDate date,
                                                              @RequestParam(value = "currency", required = true) String currency,
                                                              @RequestParam(value = "amount", required = true) BigDecimal amount) {

        LOGGER.info("ExchangeController::getEuroConverter");
        return new ResponseEntity<>(exchangeService.getEuroConverter(date, currency.toUpperCase(), amount), HttpStatus.OK);
    }
}