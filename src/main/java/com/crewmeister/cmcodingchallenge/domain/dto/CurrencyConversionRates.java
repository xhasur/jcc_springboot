package com.crewmeister.cmcodingchallenge.domain.dto;

public class CurrencyConversionRates {
    private double conversionRate;

    public CurrencyConversionRates(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
}
