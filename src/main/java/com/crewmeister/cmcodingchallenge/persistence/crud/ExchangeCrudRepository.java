package com.crewmeister.cmcodingchallenge.persistence.crud;

import com.crewmeister.cmcodingchallenge.persistence.ExchangeRepository;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public interface ExchangeCrudRepository extends JpaRepository<ExchangeRateEntity, Long> {

    List<ExchangeRateEntity> findAllByDate(LocalDate date);

    List<ExchangeRateEntity> findAllByDateAndCurrency(LocalDate date, Currency currency);

}
