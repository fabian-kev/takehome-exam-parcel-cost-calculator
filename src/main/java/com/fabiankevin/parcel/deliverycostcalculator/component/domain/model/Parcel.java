package com.fabiankevin.parcel.deliverycostcalculator.component.domain.model;

import lombok.Data;

@Data
public class Parcel {
    private String voucherCode;
    private Double weight;
    private Volume volume;
}
