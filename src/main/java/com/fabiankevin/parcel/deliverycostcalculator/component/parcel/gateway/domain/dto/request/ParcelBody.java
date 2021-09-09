package com.fabiankevin.parcel.deliverycostcalculator.component.parcel.gateway.domain.dto.request;

import lombok.Data;

@Data
public class ParcelBody {
    private String voucherCode;
    private Volume volume;
    private Double weight;

    @Data
    public static class Volume {
        private Double height;
        private Double width;
        private Double length;
    }
}
