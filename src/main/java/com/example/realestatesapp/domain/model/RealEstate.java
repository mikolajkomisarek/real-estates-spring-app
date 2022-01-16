package com.example.realestatesapp.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RealEstate(
        String id,
        String type,
        BigDecimal price,
        String description,
        float area,
        int rooms,
        LocalDate createDate
) {
}
