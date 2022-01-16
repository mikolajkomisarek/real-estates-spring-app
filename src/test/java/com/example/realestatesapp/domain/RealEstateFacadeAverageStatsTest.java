package com.example.realestatesapp.domain;

import com.example.realestatesapp.domain.model.RealEstateRegionType;
import com.example.realestatesapp.domain.model.RealEstateSizeType;
import com.example.realestatesapp.domain.model.RealEstateStatsResponse;
import com.example.realestatesapp.domain.model.RealEstateType;
import com.example.realestatesapp.infra.dao.RealEstateConnector;
import com.example.realestatesapp.infra.dao.RealEstateDao;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RealEstateFacadeAverageStatsTest {

    public static WireMockServer wireMockServer = new WireMockServer(options().port(2345));

    private RealEstateFacade realEstateFacade;
    private String data;

    @BeforeAll
    static void setUp() {
        wireMockServer.start();

    }

    @AfterAll
    static void tearDown() {
        wireMockServer.shutdown();
    }

    @BeforeEach
    void initialize() throws IOException {
        RealEstateDao realEstateDao = new RealEstateDao();
        RealEstateDataImporterService realEstateDataImporterService = new RealEstateDataImporterService(
                new RealEstateConnector(wireMockServer.baseUrl()),
                realEstateDao
        );
        RealEstateStatistic realEstateStatistic = new RealEstateStatistic(
                realEstateDao
        );
        realEstateFacade = new RealEstateFacade(realEstateDataImporterService,
                realEstateStatistic);

        this.data = new String(Objects.requireNonNull(RealEstateFacadeAverageStatsTest.class.getClassLoader().getResourceAsStream("example-data.json")).readAllBytes());
    }

    @Test
    void should_calculate_avg() {
        //given
        wireMockServer.stubFor(get(anyUrl())
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                data
                        )
                ));
        realEstateFacade.importRealEstateData();

        //when
        RealEstateStatsResponse statistic = realEstateFacade.getAverageRealEstatePrice(
                RealEstateRegionType.LUBL,
                RealEstateSizeType.S,
                List.of(RealEstateType.DETACHED_HOUSE, RealEstateType.FLAT),
                LocalDate.of(2022,1, 1),
                LocalDate.of(2022,1,30)
                );

        //then
        assertEquals(345534.0, statistic.avgValue());
    }


}
