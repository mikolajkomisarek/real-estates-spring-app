package com.example.realestatesapp.domain.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public enum RealEstateType {

    DETACHED_HOUSE("detached_house"),
    FLAT("flat"),
    SEMI_DETACHED_HOUSE("semi_detached_house"),
    TERRACED_HOUSE("terraced_house");

    private final String requestCode;

    RealEstateType(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public static Optional<RealEstateType> findRealEstateTypeByRequestCode(String requestCode) {
        return Arrays.stream(RealEstateType.values())
                .filter(realEstateType -> realEstateType.getRequestCode().equals(requestCode))
                .findFirst();
    }

    public static List<RealEstateType> fromListString(List<String> types) {
       return types.stream()
                .map(requestCode -> findRealEstateTypeByRequestCode(requestCode).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }
}
