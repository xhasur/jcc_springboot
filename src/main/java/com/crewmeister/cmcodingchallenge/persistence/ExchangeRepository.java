package com.crewmeister.cmcodingchallenge.persistence;

import com.crewmeister.cmcodingchallenge.domain.repository.IExchangeRepository;
import com.crewmeister.cmcodingchallenge.persistence.crud.ExchangeCrudRepository;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRateEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Set;
@Repository
public class ExchangeRepository implements IExchangeRepository {

    private ExchangeCrudRepository exchangeCrudRepository;


    public ExchangeRepository(ExchangeCrudRepository exchangeCrudRepository) {
        this.exchangeCrudRepository = exchangeCrudRepository;
    }

    @Override
    public void saveAll(Set<ExchangeRateEntity> exchanges) {
        this.exchangeCrudRepository.saveAll(exchanges);
    }

    @Override
    public List<ExchangeRateEntity> getAllExchangesRates() {
        return this.exchangeCrudRepository.findAll();
    }

    @Override
    public List<ExchangeRateEntity> getExchangeRatesForDate(LocalDate date) {
        return  this.exchangeCrudRepository.findAllByDate(date);
    }

    @Override
    public List<ExchangeRateEntity> getEuroConverter(LocalDate date, Currency currency) {
        return this.exchangeCrudRepository.findAllByDateAndCurrency(date, currency);
    }

}
