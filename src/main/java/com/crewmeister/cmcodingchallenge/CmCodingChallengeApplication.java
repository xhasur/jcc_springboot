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


	public static void main(String[] args) {
		SpringApplication.run(CmCodingChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}




}
