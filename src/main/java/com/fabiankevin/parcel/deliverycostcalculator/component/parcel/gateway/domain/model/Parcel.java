package com.fabiankevin.parcel.deliverycostcalculator.component.parcel.gateway.domain.model;

import lombok.Data;

@Data
public class Parcel {
    private String voucherCode;
    private Double weight;
    private Volume volume;
}
