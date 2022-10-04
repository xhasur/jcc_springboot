package com.crewmeister.cmcodingchallenge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDto {

    private Currency currency;

    private BigDecimal rate;

    private LocalDate date;

    private String description;

}
