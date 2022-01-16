package com.example.realestatesapp.infra.api;


import com.example.realestatesapp.domain.RealEstateFacade;
import com.example.realestatesapp.domain.model.RealEstateRegionType;
import com.example.realestatesapp.domain.model.RealEstateStatsResponse;
import com.example.realestatesapp.domain.model.RealEstateSizeType;
import com.example.realestatesapp.domain.model.RealEstateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
class RealEstatesStatsApi {

    private final RealEstateFacade realEstateFacade;

    @Autowired
    RealEstatesStatsApi(RealEstateFacade realEstateFacade) {
        this.realEstateFacade = realEstateFacade;
    }

    @GetMapping("{regionID}")
    RealEstateStatsResponse averageRealEstatePrice(
            @PathVariable RealEstateRegionType regionID,
            @RequestParam RealEstateSizeType size,
            @RequestParam List<String> types,
            @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate dateSince,
            @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate dateUntil) {
        return this.realEstateFacade.getAverageRealEstatePrice(regionID, size, RealEstateType.fromListString(types), dateSince, dateUntil);
    }

}
