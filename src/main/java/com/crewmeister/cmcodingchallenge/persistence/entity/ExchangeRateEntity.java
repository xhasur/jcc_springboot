package com.crewmeister.cmcodingchallenge.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateEntity {

    public ExchangeRateEntity(Currency currency, BigDecimal rate, LocalDate date, String description) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Currency currency;
    private BigDecimal rate;

    private LocalDate date;

    private String description;

}


