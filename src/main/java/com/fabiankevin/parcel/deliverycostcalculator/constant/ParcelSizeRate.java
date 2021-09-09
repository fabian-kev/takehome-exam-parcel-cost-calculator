package com.fabiankevin.parcel.deliverycostcalculator.constant;

import com.fabiankevin.parcel.deliverycostcalculator.component.parcel.gateway.domain.model.Volume;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ParcelSizeRate {
    SMALL(0.03),
    MEDIUM(0.04),
    LARGE(0.05)
    ;
    private Double amount;


    public static Double compute(Volume volume, ParcelSizeRate parcelSizeRate){
        return parcelSizeRate.amount * volume.getVolumeValue();
    }
}
