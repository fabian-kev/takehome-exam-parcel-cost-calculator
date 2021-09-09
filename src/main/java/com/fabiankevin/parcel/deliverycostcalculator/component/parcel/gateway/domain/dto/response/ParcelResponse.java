package com.fabiankevin.parcel.deliverycostcalculator.component.parcel.gateway.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcelResponse {
    private Double price;
    private String voucherCodeUsed;
    private Double discountRate;
}
