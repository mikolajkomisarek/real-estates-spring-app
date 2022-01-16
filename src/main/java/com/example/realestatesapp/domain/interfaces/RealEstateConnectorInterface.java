package com.example.realestatesapp.domain.interfaces;

import com.example.realestatesapp.domain.model.RealEstateRegionType;
import com.example.realestatesapp.domain.model.RealEstateResponse;


public interface RealEstateConnectorInterface {

    RealEstateResponse getRealEstateData(RealEstateRegionType realEstateRegionType, int page);
}
