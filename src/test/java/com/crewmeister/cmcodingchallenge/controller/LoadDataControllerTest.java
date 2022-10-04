package com.crewmeister.cmcodingchallenge.controller;

import com.crewmeister.cmcodingchallenge.domain.dto.CurrencyDto;
import com.crewmeister.cmcodingchallenge.domain.service.CurrencyService;
import com.crewmeister.cmcodingchallenge.domain.service.ExchangeService;
import com.crewmeister.cmcodingchallenge.web.controller.CurrencyController;
import com.crewmeister.cmcodingchallenge.web.controller.LoadDataController;
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

@WebMvcTest(controllers = LoadDataController.class)
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application.properties")
public class LoadDataControllerTest {

    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private ExchangeService exchangeService;

    @Autowired
    MockMvc mockMvc;


    @Test
    @DisplayName("Test getting data")
    public void testGetCurrencies() throws Exception {
        mockMvc.perform(get("/api/loadData"))
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName("Test return not found when the url is not correct")
    public void testGetExceptionUrl() throws Exception {
        mockMvc.perform(get("/loadData"))
                .andExpect(status().isNotFound());
    }


}
