package com.fabiankevin.parcel.deliverycostcalculator.component.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ParcelWeightRate {
    HEAVY_PARCEL(20.0);
    private Double weight;
}
