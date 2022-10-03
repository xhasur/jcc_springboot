package com.crewmeister.cmcodingchallenge.persistence;

import com.crewmeister.cmcodingchallenge.domain.repository.ICurrencyRepository;
import com.crewmeister.cmcodingchallenge.persistence.crud.CurrencyCrudRepository;
import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class CurrencyRepository implements ICurrencyRepository {


    private CurrencyCrudRepository currencyCrudRepository;

    public CurrencyRepository(CurrencyCrudRepository currencyCrudRepository) {
        this.currencyCrudRepository = currencyCrudRepository;
    }

    @Override
    public List<CurrencyEntity> getCurrencies() {
        return this.currencyCrudRepository.findAll();
    }

    @Override
    public void saveAll(Set<CurrencyEntity> currencies) {
        this.currencyCrudRepository.saveAll(currencies);
    }
}
