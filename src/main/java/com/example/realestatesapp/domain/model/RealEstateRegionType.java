package com.example.realestatesapp.domain.model;

public enum RealEstateRegionType {

    DLN_WROC_C("Dolnośląskie - Wrocław centrum"),
    DLN_WROC_PC("Dolnośląskie - Wrocław poza centrum"),
    DLN_POZA_WROC("Dolnośląskie - poza Wrocławiem"),
    SL_POL("Śląskie - południe"),
    SL_KATO("Śląskie - Katowice"),
    SL_PN("Śląskie - północ"),
    M_WAW_CE("Mazowieckie - Warszawa Centrum"),
    M_WAW_W("Mazowieckie - Warszawa wschód"),
    M_WAW_Z("Mazowieckie - Warszaawa - zachód"),
    LUBL("Lubelskie - Lublin"),
    LUBL_INNE("Lubelskie - poza Lublinem");

    private final String description;

    RealEstateRegionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
