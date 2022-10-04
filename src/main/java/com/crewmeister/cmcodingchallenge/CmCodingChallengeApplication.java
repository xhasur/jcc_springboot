package com.crewmeister.cmcodingchallenge;

import com.crewmeister.cmcodingchallenge.domain.service.CurrencyService;
import com.crewmeister.cmcodingchallenge.domain.service.ExchangeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class CmCodingChallengeApplication implements CommandLineRunner {

	private static Logger LOGGER = LogManager.getLogger(CmCodingChallengeApplication.class);

    private CurrencyService currencyService;

	private ExchangeService exchangeService;

	public CmCodingChallengeApplication(CurrencyService currencyService, ExchangeService exchangeService) {
		this.currencyService = currencyService;
		this.exchangeService = exchangeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CmCodingChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.currencyService.loadCurrencies();
		this.exchangeService.loadRates();
		// LoadDataService.readExchangeFromCsv("https://api.statistiken.bundesbank.de/rest/download/BBEX3/D.AUD.EUR.BB.AC.000?format=csv&lang=en");
		// this.currencyService.getAllCurrencies().forEach(LOGGER::info);
		//this.exchangeService.getAllCurriencyExchanges().forEach(LOGGER::info);
	}




}
