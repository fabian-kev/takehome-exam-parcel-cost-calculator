package com.fabiankevin.parcel.deliverycostcalculator.component.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParcelWeightRule {
    HEAVY_PARCEL(20.0),
    REJECT_PARCEL(50.0);
    private Double weight;
}
