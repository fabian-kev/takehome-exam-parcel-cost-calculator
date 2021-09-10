package com.fabiankevin.parcel.deliverycostcalculator.interactor;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Parcel;

public interface CalculateDeliveryCost {
    Double execute(Parcel parcel);
}
