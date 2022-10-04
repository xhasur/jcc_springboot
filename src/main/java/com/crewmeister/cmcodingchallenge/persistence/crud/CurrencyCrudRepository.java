package com.crewmeister.cmcodingchallenge.persistence.crud;

import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyCrudRepository extends JpaRepository<CurrencyEntity, Long>{


}
