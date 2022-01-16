package com.example.realestatesapp.domain;

import com.example.realestatesapp.infra.dao.RealEstateConnector;
import com.example.realestatesapp.infra.dao.RealEstateDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RealEstateConfig {

    @Value("${real.estates.base.url}")
    private String baseUrl;

    @Bean
    public RealEstateFacade realEstateFacade() {
        RealEstateDataImporterService realEstateDataImporterService = new RealEstateDataImporterService(
                new RealEstateConnector(baseUrl),
                new RealEstateDao()
        );
        RealEstateStatistic realEstateStatistic = new RealEstateStatistic(
                new RealEstateDao()
        );
        return new RealEstateFacade(realEstateDataImporterService,
                realEstateStatistic);
    }
}
