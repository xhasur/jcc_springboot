package com.crewmeister.cmcodingchallenge.persistence.crud;

import com.crewmeister.cmcodingchallenge.persistence.ExchangeRepository;
import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import com.crewmeister.cmcodingchallenge.persistence.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeCrudRepository extends JpaRepository<ExchangeRate, Long> {


}
