package com.example.realestatesapp.infra.dao;

import com.example.realestatesapp.domain.interfaces.RealEstateDaoInterface;
import com.example.realestatesapp.domain.model.RealEstateDto;
import com.example.realestatesapp.domain.model.RealEstateRegionType;
import com.example.realestatesapp.domain.model.RealEstateSizeType;
import com.example.realestatesapp.domain.model.RealEstateType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RealEstateDao implements RealEstateDaoInterface {


    private final HashMap<String, RealEstateDto> realEstateHashMap = new HashMap<>();

    @Override
    public List<RealEstateDto> save(List<RealEstateDto> realEstates) {
        realEstates
                .forEach(realEstate -> realEstateHashMap.put(UUID.randomUUID().toString(), realEstate));
        return getAll();
    }

    @Override
    public List<RealEstateDto> getAll() {
        return realEstateHashMap.entrySet()
                .parallelStream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<RealEstateDto> getAll(RealEstateRegionType regionID,
                                      RealEstateSizeType size,
                                      List<RealEstateType> types,
                                      LocalDate dateSince,
                                      LocalDate dateUntil) {
        return getAll()
                .stream()
                .filter(realEstate -> realEstate.isBelongsToRealEstateRegion(regionID))
                .filter(realEstate -> realEstate.isRealEstateBelongsToSizeType(size))
                .filter(realEstate -> realEstate.isBelongsToRealEstateTypes(types))
                .filter(realEstate -> realEstate.isBetweenDate(dateSince, dateUntil))
                .toList();
    }
}
