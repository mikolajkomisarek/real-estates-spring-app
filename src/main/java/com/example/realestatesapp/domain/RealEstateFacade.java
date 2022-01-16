package com.example.realestatesapp.domain;

import com.example.realestatesapp.domain.model.*;


import java.time.LocalDate;
import java.util.List;

public class RealEstateFacade {

    private final RealEstateDataImporterService realEstateDataImporterService;
    private final RealEstateStatistic realEstateStatistic;

    public RealEstateFacade(RealEstateDataImporterService realEstateDataImporterService,
                            RealEstateStatistic realEstateStatistic) {
        this.realEstateDataImporterService = realEstateDataImporterService;
        this.realEstateStatistic = realEstateStatistic;
    }


    public RealEstateStatsResponse getAverageRealEstatePrice(RealEstateRegionType regionID,
                                                             RealEstateSizeType size,
                                                             List<RealEstateType> types,
                                                             LocalDate dateSince,
                                                             LocalDate dateUntil) {
        return this.realEstateStatistic.getAverageRealEstatePrice(regionID,
                size,
                types,
                dateSince,
                dateUntil);
    }

    public List<RealEstateDto> importRealEstateData() {
        return this.realEstateDataImporterService.importRealEstateData();
    }
}
