package com.crewmeister.cmcodingchallenge.domain.repository;

import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;

import java.util.List;
import java.util.Set;

public interface ICurrencyRepository {

    List<CurrencyEntity> getCurrencies();

    void saveAll(Set<CurrencyEntity> currencies);

}
