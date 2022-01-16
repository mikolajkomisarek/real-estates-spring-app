package com.example.realestatesapp.domain;

import com.example.realestatesapp.domain.interfaces.RealEstateConnectorInterface;
import com.example.realestatesapp.domain.interfaces.RealEstateDaoInterface;
import com.example.realestatesapp.domain.model.RealEstate;
import com.example.realestatesapp.domain.model.RealEstateDto;
import com.example.realestatesapp.domain.model.RealEstateRegionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class RealEstateDataImporterService {
    private final static int INITIAL_PAGE = 0;
    private final RealEstateConnectorInterface realEstateConnector;
    private final RealEstateDaoInterface realEstateDao;
    private final RealEstateRegionType[] realEstateRegionTypes = RealEstateRegionType.values();

    RealEstateDataImporterService(RealEstateConnectorInterface realEstateConnectorInterface,
                                  RealEstateDaoInterface realEstateDaoInterface) {
        this.realEstateConnector = realEstateConnectorInterface;
        this.realEstateDao = realEstateDaoInterface;
    }

    protected List<RealEstateDto> importRealEstateData() {
        return realEstateDao.save(importRealEstateDataFromAllAvailableRegions());
    }

    private List<RealEstateDto> importRealEstateDataFromAllAvailableRegions() {
        return Arrays.stream(realEstateRegionTypes)
                .map(this::getAllRealEstateDataFromSelectedRegion)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<RealEstateDto> getAllRealEstateDataFromSelectedRegion(RealEstateRegionType realEstateRegionType) {
        return getAllRealEstateDataFromSelectedRegion(realEstateRegionType, INITIAL_PAGE, new ArrayList<>())
                .stream()
                .map(realEstate -> new RealEstateDto(realEstate, realEstateRegionType))
                .collect(Collectors.toList());
    }

    private List<RealEstate> getAllRealEstateDataFromSelectedRegion(RealEstateRegionType realEstateRegionType,
                                                                    int page,
                                                                    List<RealEstate> data) {
        var realEstateResponse = realEstateConnector
                .getRealEstateData(realEstateRegionType, page);

        if (realEstateResponse.hasNextPage(page)) {
            data.addAll(realEstateResponse.data());
            getAllRealEstateDataFromSelectedRegion(realEstateRegionType, page + 1, data);
        }

        return data;
    }

}
