package com.example.realestatesapp.domain;

import com.example.realestatesapp.domain.interfaces.RealEstateDaoInterface;
import com.example.realestatesapp.domain.model.*;

import java.time.LocalDate;
import java.util.List;

class RealEstateStatistic {

    private final RealEstateDaoInterface realEstateDao;

    RealEstateStatistic(RealEstateDaoInterface realEstateDaoInterface) {
        this.realEstateDao = realEstateDaoInterface;
    }

    RealEstateStatsResponse getAverageRealEstatePrice(RealEstateRegionType regionID,
                                                      RealEstateSizeType size,
                                                      List<RealEstateType> types,
                                                      LocalDate dateSince,
                                                      LocalDate dateUntil) {
        return new RealEstateStatsResponse(getAverage(regionID, size, types, dateSince, dateUntil));
    }


    private double getAverage(RealEstateRegionType regionID, RealEstateSizeType size, List<RealEstateType> types, LocalDate dateSince,
                              LocalDate dateUntil) {
        return this.realEstateDao.getAll(regionID, size, types, dateSince, dateUntil)
                .parallelStream()
                .mapToDouble(realEstate -> realEstate.price().doubleValue())
                .average()
                .orElse(0);
    }

}
