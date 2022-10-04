package com.crewmeister.cmcodingchallenge.persistence;

import com.crewmeister.cmcodingchallenge.domain.dto.ExchangeDto;
import com.crewmeister.cmcodingchallenge.domain.repository.IExchangeRepository;
import com.crewmeister.cmcodingchallenge.persistence.crud.ExchangeCrudRepository;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRateEntity;
import com.crewmeister.cmcodingchallenge.persistence.mapper.ExchangeRateMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ExchangeRepository implements IExchangeRepository {

    private ExchangeCrudRepository exchangeCrudRepository;

    private ExchangeRateMapper exchangeRateMapper;

    public ExchangeRepository(ExchangeCrudRepository exchangeCrudRepository, ExchangeRateMapper exchangeRateMapper) {
        this.exchangeCrudRepository = exchangeCrudRepository;
        this.exchangeRateMapper = exchangeRateMapper;
    }

    @Override
    public void saveAll(Set<ExchangeDto> exchanges) {
        Set<ExchangeRateEntity> exchangesEntities = exchanges.stream()
                .map(currencyDto -> exchangeRateMapper.toExchangeEntity(currencyDto))
                .collect(Collectors.toSet());
        this.exchangeCrudRepository.saveAll(exchangesEntities);
    }

    @Override
    public List<ExchangeDto> getAllExchangesRates() {
        return exchangeRateMapper.toExchanges(this.exchangeCrudRepository.findAll());
    }

    @Override
    public List<ExchangeDto> getExchangeRatesForDate(LocalDate date) {
        return exchangeRateMapper.toExchanges(this.exchangeCrudRepository.findAllByDate(date));
    }

    @Override
    public List<ExchangeDto> getEuroConverter(LocalDate date, Currency currency) {
        return exchangeRateMapper.toExchanges(this.exchangeCrudRepository.findAllByDateAndCurrency(date, currency));
    }

}
