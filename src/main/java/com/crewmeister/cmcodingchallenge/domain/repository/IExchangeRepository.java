package com.crewmeister.cmcodingchallenge.domain.repository;

import com.crewmeister.cmcodingchallenge.domain.dto.ExchangeDto;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRateEntity;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Set;

public interface IExchangeRepository {
    void saveAll(Set<ExchangeDto> currencies);

    List<ExchangeDto> getAllExchangesRates();

     List<ExchangeDto> getAllExchangesRatesPageable(int page, int size);

    List<ExchangeDto> getExchangeRatesForDate(LocalDate date);

    List<ExchangeDto> getEuroConverter(LocalDate date, Currency currency);
}
