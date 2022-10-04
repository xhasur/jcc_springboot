package com.crewmeister.cmcodingchallenge.persistence.crud;

import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRateEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public interface ExchangeCrudRepository extends PagingAndSortingRepository<ExchangeRateEntity, Long> {

    List<ExchangeRateEntity> findAllByDate(LocalDate date);

    List<ExchangeRateEntity> findAllByDateAndCurrency(LocalDate date, Currency currency);

    List<ExchangeRateEntity> findAll();

}
