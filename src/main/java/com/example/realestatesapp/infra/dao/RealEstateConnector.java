package com.example.realestatesapp.infra.dao;

import com.example.realestatesapp.domain.interfaces.RealEstateConnectorInterface;
import com.example.realestatesapp.domain.model.RealEstateRegionType;
import com.example.realestatesapp.domain.model.RealEstateResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class RealEstateConnector implements RealEstateConnectorInterface {

    private final WebClient webClient;

    public RealEstateConnector(String baseUrl) {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public RealEstateResponse getRealEstateData(RealEstateRegionType realEstateRegionType, int page) {
        return this.webClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/api/real-estates/{regionID}")
                                .queryParam("page", page)
                                .build(realEstateRegionType.name()))
                .retrieve()
                .toEntity(RealEstateResponse.class)
                .blockOptional()
                .map(HttpEntity::getBody)
                .orElse(new RealEstateResponse());
    }
}
