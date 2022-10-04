package com.crewmeister.cmcodingchallenge.controller;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyDto;
import com.crewmeister.cmcodingchallenge.domain.service.CurrencyService;
import com.crewmeister.cmcodingchallenge.web.controller.CurrencyController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CurrencyController.class)
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application.properties")
public class CurrencyControllerTest {

    @MockBean
    private CurrencyService currencyService;


    @Autowired
    MockMvc mockMvc;


    private List<CurrencyDto> currencies;

    @BeforeEach
    public void setup() {
        CurrencyDto currencyNOK = new CurrencyDto(Currency.getInstance("NOK"));
        CurrencyDto currencyCAD = new CurrencyDto(Currency.getInstance("BRL"));
        CurrencyDto currencyCHF = new CurrencyDto(Currency.getInstance("CHF"));
        currencies = new ArrayList<>(Arrays.asList(currencyNOK,currencyCAD,currencyCHF));
    }
    @Test
    @DisplayName("Test getting currencies")
    public void testGetCurrencies() throws Exception {
        Mockito.when(currencyService.getAllCurrencies()).thenReturn(currencies);
        mockMvc.perform(get("/api/currencies"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].currency").value("NOK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].currency").value("BRL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].currency").value("CHF"));
    }

    @Test
    @DisplayName("Test getting currencies when the service return empty")
    public void testGetEmptyCurrencies() throws Exception {
        Mockito.when(currencyService.getAllCurrencies()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/currencies"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
    }

    @Test
    @DisplayName("Test exception getting currencies")
    public void testGetException() throws Exception {
        Mockito.when(currencyService.getAllCurrencies()).thenThrow(NullPointerException.class );
        try {
            mockMvc.perform(get("/api/currencies"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
        }catch(Exception ex) {
            Assertions.assertThat(ex.getCause() instanceof NullPointerException).isTrue();
        }

    }

    @Test
    @DisplayName("Test return not found when the url is not correct")
    public void testGetExceptionUrl() throws Exception {
        Mockito.when(currencyService.getAllCurrencies()).thenReturn(Collections.emptyList() );
        mockMvc.perform(get("/currencies"))
                .andExpect(status().isNotFound());
    }


}
