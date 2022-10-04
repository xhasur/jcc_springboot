package com.crewmeister.cmcodingchallenge.repository;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyDto;
import com.crewmeister.cmcodingchallenge.domain.repository.ICurrencyRepository;
import com.crewmeister.cmcodingchallenge.domain.service.CurrencyService;
import com.crewmeister.cmcodingchallenge.persistence.CurrencyRepository;
import com.crewmeister.cmcodingchallenge.persistence.crud.CurrencyCrudRepository;
import com.crewmeister.cmcodingchallenge.persistence.entity.CurrencyEntity;
import com.crewmeister.cmcodingchallenge.persistence.mapper.CurrencyMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyRepositoryTest {


    @InjectMocks
    private CurrencyRepository currencyRepository;

    @Mock
    private CurrencyCrudRepository currencyCrudRepository;

    @Mock
    private CurrencyMapper currencyMapper;

    private List<CurrencyDto> currencies;

    private List<CurrencyEntity> currenciesE;


    @BeforeEach
    public void setup() {
        CurrencyDto currencyNOK = new CurrencyDto(Currency.getInstance("NOK"));
        CurrencyDto currencyCAD = new CurrencyDto(Currency.getInstance("BRL"));
        CurrencyDto currencyCHF = new CurrencyDto(Currency.getInstance("CHF"));
        currencies = new ArrayList<>(Arrays.asList(currencyNOK,currencyCAD,currencyCHF));


        CurrencyEntity currencyENOK = new CurrencyEntity(Currency.getInstance("NOK"));
        CurrencyEntity currencyECAD = new CurrencyEntity(Currency.getInstance("BRL"));
        CurrencyEntity currencyECHF = new CurrencyEntity(Currency.getInstance("CHF"));
        currenciesE = new ArrayList<>(Arrays.asList(currencyENOK,currencyECAD,currencyECHF));

    }



    @Test
    @DisplayName("Test getCurrencies")
    public void testGetCurrencies() {
        when(currencyCrudRepository.findAll()).thenReturn(currenciesE);
        when(currencyMapper.toCurrencies(anyObject())).thenReturn(currencies);
        List<CurrencyDto> response = currencyRepository.getCurrencies();
        assertEquals(3, response.size());
    }

    @Test
    @DisplayName("Test saveCurrencies")
    public void testSaveCurrencies() {
        Set<CurrencyDto> currenciesSet = new HashSet<CurrencyDto>(currencies);
        when(currencyMapper.toCurrencyEntity(anyObject())).thenReturn(new CurrencyEntity(Currency.getInstance("CHF")));
        currencyRepository.saveAll(currenciesSet);
        verify(currencyCrudRepository).saveAll(anySet());
    }


}
