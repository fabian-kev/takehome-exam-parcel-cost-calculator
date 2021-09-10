package com.fabiankevin.parcel.deliverycostcalculator.component.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParcelVolumeRule {
    SMALL(0.0, 1500.0),
    MEDIUM(1500.0, 2500.0),
    LARGE(2500.0, Double.MAX_VALUE);
    private Double cmMin;
    private Double cmMax;
}
