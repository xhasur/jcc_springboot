package com.crewmeister.cmcodingchallenge.domain.repository;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyDto;
import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;

import java.util.List;
import java.util.Set;

public interface ICurrencyRepository {

    List<CurrencyDto> getCurrencies();

    void saveAll(Set<CurrencyDto> currencies);

}
