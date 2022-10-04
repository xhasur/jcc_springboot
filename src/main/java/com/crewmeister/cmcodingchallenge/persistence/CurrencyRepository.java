package com.crewmeister.cmcodingchallenge.persistence;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyDto;
import com.crewmeister.cmcodingchallenge.domain.repository.ICurrencyRepository;
import com.crewmeister.cmcodingchallenge.persistence.crud.CurrencyCrudRepository;
import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import com.crewmeister.cmcodingchallenge.persistence.mapper.CurrencyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CurrencyRepository implements ICurrencyRepository {


    private CurrencyCrudRepository currencyCrudRepository;

    private CurrencyMapper currencyMapper;

    public CurrencyRepository(CurrencyCrudRepository currencyCrudRepository,
                              CurrencyMapper currencyMapper) {
        this.currencyCrudRepository = currencyCrudRepository;
        this.currencyMapper = currencyMapper;
    }

    @Override
    public List<CurrencyDto> getCurrencies() {
        return currencyMapper.toCurrencies(this.currencyCrudRepository.findAll());
    }

    @Override
    public void saveAll(Set<CurrencyDto> currencies) {
        Set<CurrencyEntity> currenciesEntity =  currencies.stream()
                .map(currencyDto -> currencyMapper.toCurrencyEntity(currencyDto))
                .collect(Collectors.toSet());
        this.currencyCrudRepository.saveAll(currenciesEntity);
    }
}
