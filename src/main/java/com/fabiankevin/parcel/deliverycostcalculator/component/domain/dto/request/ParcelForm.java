package com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Data
public class ParcelForm {
    private String voucherCode;
    @NotNull(message = "Volume object is required")
    private Volume volume;
    @NotNull(message = "Weight is required")
    private Double weight;

    @Data
    @Valid
    public static class Volume {
        @NotNull(message = "Height is required")
        private Double height;
        @NotNull(message = "Width is required")
        private Double width;
        @NotNull(message = "Length is required")
        private Double length;
    }
}
