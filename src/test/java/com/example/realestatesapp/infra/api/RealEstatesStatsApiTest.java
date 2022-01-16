package com.example.realestatesapp.infra.api;

import com.example.realestatesapp.domain.RealEstateConfig;
import com.example.realestatesapp.domain.RealEstateFacade;
import com.example.realestatesapp.domain.model.RealEstateRegionType;
import com.example.realestatesapp.domain.model.RealEstateSizeType;
import com.example.realestatesapp.domain.model.RealEstateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = RealEstatesStatsApi.class)
public class RealEstatesStatsApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RealEstateFacade realEstateFacade;

    @BeforeEach
    void initialize() {
        realEstateFacade = new RealEstateConfig().realEstateFacade();

    }

    @Test
    public void should_return_http_status_ok_when_statistic_api_called() throws Exception {
        mockMvc.perform(get("/{regionID}", RealEstateRegionType.LUBL.name())
                        .queryParam("size", RealEstateSizeType.M.name())
                        .queryParam("types", RealEstateType.DETACHED_HOUSE.getRequestCode(), RealEstateType.FLAT.getRequestCode())
                        .queryParam("dateSince", "20210501")
                        .queryParam("dateUntil", "20210910")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_http_status_400_error_when_date_params_not_passed() throws Exception {
        mockMvc.perform(get("/{regionID}", RealEstateRegionType.LUBL.name())
                        .queryParam("size", RealEstateSizeType.M.name())
                        .queryParam("types", RealEstateType.DETACHED_HOUSE.getRequestCode(), RealEstateType.FLAT.getRequestCode())
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

    // Add tests related to checked body....




}
