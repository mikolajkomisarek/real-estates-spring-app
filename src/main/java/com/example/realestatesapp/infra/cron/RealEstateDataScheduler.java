package com.example.realestatesapp.infra.cron;

import com.example.realestatesapp.domain.RealEstateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class RealEstateDataScheduler {

    private final RealEstateFacade realEstateFacade;

    @Autowired
    RealEstateDataScheduler(RealEstateFacade realEstateFacade) {
        this.realEstateFacade = realEstateFacade;
    }

    @Scheduled(cron = "0 0 21 * * *")
    void importRealEstateData() {
        realEstateFacade.importRealEstateData();
    }
}
