package com.fabiankevin.parcel.deliverycostcalculator.interactor;

import com.fabiankevin.parcel.deliverycostcalculator.component.parcel.gateway.domain.model.Parcel;

public interface CalculateDeliveryCost {
    Double execute(Parcel parcel);
}
