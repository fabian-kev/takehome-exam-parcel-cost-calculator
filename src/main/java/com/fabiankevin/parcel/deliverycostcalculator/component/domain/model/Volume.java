package com.fabiankevin.parcel.deliverycostcalculator.component.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Volume {
    private Double height;
    private Double width;
    private Double length;

    public Double getVolumeValue(){
         return height * width * length;
    }
}
