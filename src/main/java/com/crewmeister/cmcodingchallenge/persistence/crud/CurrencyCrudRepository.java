package com.crewmeister.cmcodingchallenge.persistence.crud;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyConversionRates;
import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CurrencyCrudRepository extends JpaRepository<CurrencyEntity, Long>{



}
