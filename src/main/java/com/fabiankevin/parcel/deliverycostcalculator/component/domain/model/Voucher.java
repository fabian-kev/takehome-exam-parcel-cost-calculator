package com.fabiankevin.parcel.deliverycostcalculator.component.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voucher {
    private String code;
    private Double discount;
    private LocalDate expiry;
}
