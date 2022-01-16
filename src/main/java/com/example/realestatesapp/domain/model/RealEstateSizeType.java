package com.example.realestatesapp.domain.model;

public enum RealEstateSizeType {
    S {
        @Override
        boolean isRealEstateBelongsToSizeType(float area) {
            return (area >= 18.0 && area <= 45.0);
        }
    },
    M {
        @Override
        boolean isRealEstateBelongsToSizeType(float area) {
            return (area >= 46.0 && area <= 80.0);
        }
    },
    L {
        @Override
        boolean isRealEstateBelongsToSizeType(float area) {
            return (area >= 81.0 && area <= 400.0);
        }
    };

    abstract boolean isRealEstateBelongsToSizeType(float area);


}
