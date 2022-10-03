package com.crewmeister.cmcodingchallenge.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {

    public ExchangeRate(Currency currency, String rate, String date, String description) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Currency currency;

    private String rate;

    private String date;

    private String description;

}


