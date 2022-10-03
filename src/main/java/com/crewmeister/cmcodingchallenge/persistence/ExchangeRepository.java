package com.crewmeister.cmcodingchallenge.persistence;

import com.crewmeister.cmcodingchallenge.domain.repository.IExchangeRepository;
import com.crewmeister.cmcodingchallenge.persistence.crud.ExchangeCrudRepository;
import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public class ExchangeRepository implements IExchangeRepository {

    private ExchangeCrudRepository exchangeCrudRepository;


    public ExchangeRepository(ExchangeCrudRepository exchangeCrudRepository) {
        this.exchangeCrudRepository = exchangeCrudRepository;
    }

    @Override
    public void saveAll(Set<ExchangeRate> exchanges) {
        this.exchangeCrudRepository.saveAll(exchanges);
    }

    @Override
    public List<ExchangeRate> getAllExchanges() {
        return this.exchangeCrudRepository.findAll();
    }

    @Override
    public List<ExchangeRate> getAllCurrenciesByCurrency(String currency) {
        return this.exchangeCrudRepository.findAll();
    }
}
