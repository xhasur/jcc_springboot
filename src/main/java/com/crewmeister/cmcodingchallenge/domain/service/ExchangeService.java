package com.crewmeister.cmcodingchallenge.domain.service;

import com.crewmeister.cmcodingchallenge.domain.dto.ExchangeDto;
import com.crewmeister.cmcodingchallenge.domain.repository.IExchangeRepository;
import com.crewmeister.cmcodingchallenge.persistence.ExchangeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ExchangeService {

    private static Logger LOGGER = LogManager.getLogger(ExchangeService.class);

    private LoadDataService loadDataService;

    private IExchangeRepository exchangeRepository;

    @Value("${bundesbank.currency.exchange.service.url}")
    private String url;


    public ExchangeService(LoadDataService loadDataService, ExchangeRepository exchangeRepository) {
        this.loadDataService = loadDataService;
        this.exchangeRepository = exchangeRepository;
    }

    @Transactional
    public void loadRates() {
        LOGGER.info("loadRates");
        Document doc = this.loadDataService.getBankDocument(url);
        doc.select("table tbody tr")
                .parallelStream()
                .forEach(( element -> {
                    Elements tds = element.select("td");
                    if(tds.isEmpty()) return;
                    Element dtdDescription = tds.get(2).getElementsByAttribute("href").get(0);
                    String currencyName = tds.text().split("=")[1].split("... /")[0].trim();
                    String downloadCsvLink = dtdDescription.attr("href");
                    Set<ExchangeDto> exchangeRates = this.loadDataService.readExchangeFromCsv(
                            downloadCsvLink,
                            this.loadDataService.getCurrency(currencyName));
                    this.exchangeRepository.saveAll(exchangeRates);
                }));
    }


    public List<ExchangeDto> getAllExchangesRates() {
        return this.exchangeRepository.getAllExchangesRates();
    }

    public List<ExchangeDto> getAllExchangesRates(int page, int size) {
        return this.exchangeRepository.getAllExchangesRatesPageable(page, size);
    }

    public List<ExchangeDto> getExchangeRatesForDate(LocalDate date) {
        return this.exchangeRepository.getExchangeRatesForDate(date);
    }

    public List<ExchangeDto> getEuroConverter(LocalDate date , String currency , BigDecimal amount) {
        List<ExchangeDto> allRatesByCurrency = this.exchangeRepository.getEuroConverter(date, this.loadDataService.getCurrency(currency));
        if(!allRatesByCurrency.isEmpty()){
            allRatesByCurrency.forEach(rate -> rate.setRate(rate.getRate().multiply(amount)));
            return allRatesByCurrency;
        }

        return this.exchangeRepository.getEuroConverter(date, this.loadDataService.getCurrency(currency));
    }
}
