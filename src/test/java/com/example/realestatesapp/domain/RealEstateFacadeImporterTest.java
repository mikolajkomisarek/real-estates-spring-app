package com.example.realestatesapp.domain;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.realestatesapp.domain.model.RealEstateDto;
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
import java.util.List;
import java.util.Objects;

public class RealEstateFacadeImporterTest {

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
        RealEstateDataImporterService realEstateDataImporterService = new RealEstateDataImporterService(
                new RealEstateConnector(wireMockServer.baseUrl()),
                new RealEstateDao()
        );
        RealEstateStatistic realEstateStatistic = new RealEstateStatistic(
                new RealEstateDao()
        );
        realEstateFacade = new RealEstateFacade(realEstateDataImporterService,
                realEstateStatistic);

        this.data = new String(Objects.requireNonNull(RealEstateFacadeImporterTest.class.getClassLoader().getResourceAsStream("example-data.json")).readAllBytes());
    }

    @Test
    void should_import_real_estates_data() {
        //given
        wireMockServer.stubFor(get(anyUrl())
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                data
                        )
                ));

        //when
        List<RealEstateDto> realEstateDtoList = realEstateFacade.importRealEstateData();

        //then
        assertEquals(264, realEstateDtoList.size());
    }


}
