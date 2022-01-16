package com.example.realestatesapp.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record RealEstateDto(
        String id,
        RealEstateRegionType realEstateRegionType,
        RealEstateType realEstateType,
        BigDecimal price,
        String description,
        float area,
        int rooms,
        LocalDate createDate
) {

    public RealEstateDto(RealEstate realEstate, RealEstateRegionType realEstateRegionType) {
        this(realEstate.id(),
                realEstateRegionType,
                RealEstateType.findRealEstateTypeByRequestCode(realEstate.type()).orElse(null),
                realEstate.price(),
                realEstate.description(),
                realEstate.area(),
                realEstate.rooms(),
                realEstate.createDate());
    }

    public boolean isBetweenDate(LocalDate dateSince,
                                 LocalDate dateUntil) {
        return createDate.isAfter(dateSince) && createDate.isBefore(dateUntil);
    }

    public boolean isRealEstateBelongsToSizeType(RealEstateSizeType size) {
        return size.isRealEstateBelongsToSizeType(this.area());
    }

    public boolean isBelongsToRealEstateTypes(List<RealEstateType> types) {
        return types.stream().anyMatch(realEstateType -> realEstateType
                .getRequestCode().equals(this.realEstateType.getRequestCode()));
    }

    public boolean isBelongsToRealEstateRegion(RealEstateRegionType type) {
        return this.realEstateRegionType.equals(type);
    }
}
