package com.fabiankevin.parcel.deliverycostcalculator.interactor;

import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelSizeRate;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Parcel;
import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelWeightRate;
import org.springframework.stereotype.Service;


@Service
public class CalculateDeliveryCostImpl implements CalculateDeliveryCost {
    @Override
    public Double execute(Parcel parcel) {
        Double weight = parcel.getWeight();
        Double volume = parcel.getVolume().getVolumeValue();
        Double amount = 0.0;
        ParcelSizeRate rate;

        if( weight > 10){
            amount = ParcelWeightRate.HEAVY_PARCEL.getWeight() * weight;
        }
        if( volume < 1500 ){
            rate = ParcelSizeRate.SMALL;
        } else if(volume > 1500 && volume < 2500) {
            rate = ParcelSizeRate.MEDIUM;
        } else {
            rate = ParcelSizeRate.LARGE;
        }

        return (amount += ParcelSizeRate.compute(parcel.getVolume(), rate));
    }
}
