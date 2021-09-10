package com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.remote.response;

import lombok.Data;

@Data
public class VoucherEnvelope {
    private String code;
    private Double discount;
    private String expiry;
}
