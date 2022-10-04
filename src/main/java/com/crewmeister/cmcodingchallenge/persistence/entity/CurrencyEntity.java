package com.crewmeister.cmcodingchallenge.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Currency;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyEntity {

    public CurrencyEntity(Currency currency) {
        this.currency = currency;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Currency currency;
}
