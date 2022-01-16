package com.example.realestatesapp.domain.interfaces;

import com.example.realestatesapp.domain.model.RealEstateDto;
import com.example.realestatesapp.domain.model.RealEstateRegionType;
import com.example.realestatesapp.domain.model.RealEstateSizeType;
import com.example.realestatesapp.domain.model.RealEstateType;

import java.time.LocalDate;
import java.util.List;


public interface RealEstateDaoInterface {

    List<RealEstateDto> save(List<RealEstateDto> realEstates);

    List<RealEstateDto> getAll();

    List<RealEstateDto> getAll(RealEstateRegionType regionID,
                               RealEstateSizeType size,
                               List<RealEstateType> types,
                               LocalDate dateSince,
                               LocalDate dateUntil);
}
