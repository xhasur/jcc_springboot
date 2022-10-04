package com.crewmeister.cmcodingchallenge.controller;


import com.crewmeister.cmcodingchallenge.domain.dto.ExchangeDto;
import com.crewmeister.cmcodingchallenge.domain.service.ExchangeService;
import com.crewmeister.cmcodingchallenge.web.controller.ExchangeController;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExchangeController.class)
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application.properties")
public class ExchangeControllerTest {

    @MockBean
    private ExchangeService exchangeService;


    @Autowired
    MockMvc mockMvc;


    private List<ExchangeDto> exchanges;

    @BeforeEach
    public void setup() {

        ExchangeDto exchangeNOK = new ExchangeDto(
                Currency.getInstance("NOK"),
                new BigDecimal(327.15),
                LocalDate.of(2022, 9, 04),
                "");
        ExchangeDto exchangeCAD = new ExchangeDto(
                Currency.getInstance("CAD"),
                new BigDecimal(327.15),
                LocalDate.of(2022, 6, 11),
                "");
        ExchangeDto exchangeCHF = new ExchangeDto(Currency.getInstance("CHF"),
                new BigDecimal(327.15),
                LocalDate.of(2022, 8, 15),
                "");

        exchanges = new ArrayList<>(Arrays.asList(exchangeNOK, exchangeCAD, exchangeCHF));
    }

    @Test
    @DisplayName("Test getting exchange rates")
    public void testGetCurrencies() throws Exception {
        Mockito.when(exchangeService.getAllExchangesRates()).thenReturn(exchanges);
        mockMvc.perform(get("/api/currencyexchanges"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].currency").value("NOK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].rate").value(new BigDecimal(327.15)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].date").value(LocalDate.of(2022, 8, 15).toString()));
    }

    @Test
    @DisplayName("Test getting exchanges when the service return empty")
    public void testGetCurrenciesAndServiceGetEmpty() throws Exception {
        Mockito.when(exchangeService.getAllExchangesRates()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/currencyexchanges"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
    }

    @Test
    @DisplayName("Test exception getting currencies")
    public void testGetException() throws Exception {
        Mockito.when(exchangeService.getAllExchangesRates()).thenThrow(NullPointerException.class);
        try {
            mockMvc.perform(get("/api/currencyexchanges"))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            Assertions.assertThat(ex.getCause() instanceof NullPointerException).isTrue();
        }

    }

    @Test
    @DisplayName("Test return not found when the url is not correct")
    public void testGetExceptionUrl() throws Exception {
        Mockito.when(exchangeService.getAllExchangesRates()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/currencyexchanges"))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("Test getting exchangerates rates by date")
    public void testGetExchangeRatesByDate() throws Exception {
        Mockito.when(exchangeService.getExchangeRatesForDate(LocalDate.now())).thenReturn(exchanges);
        mockMvc.perform(get("/api/exchangerates/{date}", LocalDate.now()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].date").value(LocalDate.of(2022, 8, 15).toString()));

    }

    @Test
    @DisplayName("Test return not found when the url is not correct")
    public void testGetExceptionUrlExchangeByDate() throws Exception {
        Mockito.when(exchangeService.getExchangeRatesForDate(LocalDate.now())).thenReturn(exchanges);
        mockMvc.perform(get("/exchangerates/{date}", LocalDate.now()))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test getting exchangerates rates by date")
    public void testEuroConverter() throws Exception {
        Mockito.when(exchangeService.getEuroConverter(LocalDate.now() , "EUR", new BigDecimal(10000))).thenReturn(exchanges);
        mockMvc.perform(get("/api/euroconverter/{date}", LocalDate.now())
                        .param("currency", "USD")
                        .param("amount", "150.00"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());

    }

    @Test
    @DisplayName("Test exception using the euroconverter method")
    public void testGetExceptionEuroConverter() throws Exception {
        Mockito.when(exchangeService.getEuroConverter(LocalDate.now() , "EUR", new BigDecimal(10000))).thenThrow(NullPointerException.class);
        try {
            mockMvc.perform(get("/api/euroconverter/{date}", LocalDate.now())
                            .param("currency", "USD")
                            .param("amount", "150.00"))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            Assertions.assertThat(ex.getCause() instanceof NullPointerException).isTrue();
        }

    }

    @Test
    @DisplayName("Test return not found when the url is not correct")
    public void testGetExceptionEuroConverterBadParameters() throws Exception {
        Mockito.when(exchangeService.getEuroConverter(LocalDate.now() , "EUR", new BigDecimal(10000))).thenReturn(exchanges);
        mockMvc.perform(get("/api/euroconverter/{date}", LocalDate.now()))
                .andExpect(status().isBadRequest());
    }

}
