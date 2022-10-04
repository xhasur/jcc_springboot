package com.crewmeister.cmcodingchallenge.service;

import com.crewmeister.cmcodingchallenge.domain.dto.ExchangeDto;
import com.crewmeister.cmcodingchallenge.domain.service.ExchangeService;
import com.crewmeister.cmcodingchallenge.domain.service.LoadDataService;
import com.crewmeister.cmcodingchallenge.persistence.ExchangeRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExchangeServiceTest {

    @InjectMocks
    private ExchangeService exchangeService;

    @Mock
    private ExchangeRepository exchangeRepository;

    @Mock
    private LoadDataService loadDataService;

    private List<ExchangeDto>  exchanges;

    Document doc;

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
        doc = Jsoup.parseBodyFragment(this.getTableHtml());
    }

    @Test
    @DisplayName("Test teGetAllExchangesRates")
    public void teGetAllExchangesRates() {
        when(exchangeRepository.getAllExchangesRates()).thenReturn(exchanges);
        List<ExchangeDto> response = exchangeService.getAllExchangesRates();
        assertEquals(3, response.size());
    }

    @Test
    @DisplayName("Test getExchangeRatesForDate")
    public void getExchangeRatesForDate() {
        when(exchangeRepository.getExchangeRatesForDate(LocalDate.now())).thenReturn(exchanges);
        List<ExchangeDto> response = exchangeService.getExchangeRatesForDate(LocalDate.now());
        assertEquals(3, response.size());
    }

    @Test
    @DisplayName("Test getExchangeRatesForDate")
    public void getEuroConverter() {
        when(loadDataService.getCurrency("CAD" )).thenReturn(Currency.getInstance("CAD"));
        when(exchangeRepository.getEuroConverter(LocalDate.now(), Currency.getInstance("CAD"))).thenReturn(exchanges);
        List<ExchangeDto> response = exchangeService.getEuroConverter(LocalDate.now() , "CAD", new BigDecimal(10000));
        assertEquals(3, response.size());
    }


    @Test
    @DisplayName("Test getLoadRates but the service is never called because the page is different or maybe other page")
    public void getLoadRates() {
        when(loadDataService.getBankDocument(null)).thenReturn(Jsoup.parseBodyFragment("<td> Hello</td>"));
        exchangeService.loadRates();
        verify(exchangeRepository, never() ).saveAll(anySet());
    }

    @Test
    @DisplayName("Test loadRates obtain the information when the page is correct")
    public void testLoadCurrenciesSavingItems() {
        when(loadDataService.getBankDocument(null)).thenReturn(doc);
        exchangeService.loadRates();
        verify(exchangeRepository).saveAll(anySet());
    }

    private String getTableHtml(){
        return "<table class=\"listTableStatisticBBK_ITS\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<th> Time series\n" +
                "</th>\n" +
                "<th> Description\n" +
                "</th>\n" +
                "<th> Direct download\n" +
                "</th>\n" +
                "<th> Data basket\n" +
                "</th>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<a href=\"/dynamic/action/en/statistics/time-series-databases/time-series-databases/745582/745582?tsId=BBEX3.D.AUD.EUR.BB.AC.000&amp;listId=www_sdks_b01012_3&amp;statisticType=BBK_ITS&amp;treeAnchor=WECHSELKURSE\" target=\"_self\" title=\"Go to time series BBEX3.D.AUD.EUR.BB.AC.000\">\n" +
                "BBEX3.D.AUD.EUR.BB.AC.000 </a>\n" +
                "<a class=\"open-popup-link text-dove\" href=\"#\" title=\"Interpretation of the key BBEX3.D.AUD.EUR.BB.AC.000\" data-key=\"{&quot;results&quot;:[{&quot;tableDefinition&quot;:{&quot;tableHeadlines&quot;:[&quot;No.&quot;,&quot;Description&quot;,&quot;Key&quot;,&quot;Comment&quot;]},&quot;result&quot;:[{&quot;1&quot;:{&quot;0&quot;:&quot;Frequency (BBk)&quot;,&quot;1&quot;:&quot;D&quot;,&quot;2&quot;:&quot;Daily&quot;},&quot;2&quot;:{&quot;0&quot;:&quot;Area (ISO currency codes, list of currencies)&quot;,&quot;1&quot;:&quot;AUD&quot;,&quot;2&quot;:&quot;Australian dollar&quot;},&quot;3&quot;:{&quot;0&quot;:&quot;Currency denominator&quot;,&quot;1&quot;:&quot;EUR&quot;,&quot;2&quot;:&quot;Euro&quot;},&quot;4&quot;:{&quot;0&quot;:&quot;Content of time series&quot;,&quot;1&quot;:&quot;BB&quot;,&quot;2&quot;:&quot;ECB euro reference exchange rates&quot;},&quot;5&quot;:{&quot;0&quot;:&quot;Rate type&quot;,&quot;1&quot;:&quot;AC&quot;,&quot;2&quot;:&quot;Middle&quot;},&quot;6&quot;:{&quot;0&quot;:&quot;Suffix&quot;,&quot;1&quot;:&quot;000&quot;,&quot;2&quot;:&quot;Standard&quot;}}]}]}\">\n" +
                "<i class=\"fa fa-timetable-info\" aria-hidden=\"true\"></i>\n" +
                "</a>\n" +
                "</td>\n" +
                "<td>Euro foreign exchange reference rate of the ECB / EUR 1 = AUD ... / Australia</td>\n" +
                "<td class=\"downloadSection\">\n" +
                "<a href=\"https://api.statistiken.bundesbank.de/rest/download/BBEX3/D.AUD.EUR.BB.AC.000?format=csv&amp;lang=en\" title=\"CSV\">\n" +
                "CSV\n" +
                "</a>\n" +
                "<br>\n" +
                "<a href=\"https://www.bundesbank.de/statistic-rmi/StatisticDownload?tsId=BBEX3.D.AUD.EUR.BB.AC.000&amp;\n" +
                "its_fileFormat=sdmx&amp;\n" +
                "mode=its\" title=\"SDMX-ML 2.0\">\n" +
                "SDMX-ML 2.0\n" +
                "</a>\n" +
                "<br>\n" +
                "<a href=\"https://api.statistiken.bundesbank.de/rest/download/BBEX3/D.AUD.EUR.BB.AC.000?format=sdmx&amp;lang=en\" title=\"SDMX-ML 2.1\">\n" +
                "SDMX-ML 2.1\n" +
                "</a>\n" +
                "</td>\n" +
                "<td>\n" +
                "<div class=\"databasket\">\n" +
                "<span>\n" +
                "<a href=\"#\" role=\"button\" class=\"add d-inline-block text-inherit\" data-basket-tsid=\"BBEX3.D.AUD.EUR.BB.AC.000\" data-basket-mode=\"its\">\n" +
                "<span class=\"label\">Add</span>\n" +
                "</a>\n" +
                "</span>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";
    }

}
