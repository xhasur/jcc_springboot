package com.crewmeister.cmcodingchallenge.domain.repository;

import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRateEntity;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Set;

public interface IExchangeRepository {
    void saveAll(Set<ExchangeRateEntity> currencies);

    List<ExchangeRateEntity> getAllExchangesRates();

    List<ExchangeRateEntity> getExchangeRatesForDate(LocalDate date);

    List<ExchangeRateEntity> getEuroConverter(LocalDate date, Currency currency);
}
