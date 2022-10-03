package com.crewmeister.cmcodingchallenge.domain.repository;

import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRate;

import java.util.List;
import java.util.Set;

public interface IExchangeRepository {
    void saveAll(Set<ExchangeRate> currencies);

    List<ExchangeRate> getAllExchanges();

    List<ExchangeRate> getAllCurrenciesByCurrency(String currency);
}
