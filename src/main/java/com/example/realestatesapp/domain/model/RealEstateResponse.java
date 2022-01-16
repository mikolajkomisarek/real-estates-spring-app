package com.example.realestatesapp.domain.model;


import java.util.Collections;
import java.util.List;

public record RealEstateResponse(int totalPages, List<RealEstate> data) {

    public RealEstateResponse(){
        this(0, Collections.emptyList());
    }

    public boolean hasNextPage(int currentPage){
        return currentPage < totalPages;
    }


}
