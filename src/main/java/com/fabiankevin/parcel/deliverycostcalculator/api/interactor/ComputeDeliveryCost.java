package com.fabiankevin.parcel.deliverycostcalculator.api.interactor;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Parcel;

public interface ComputeDeliveryCost {
    Double execute(Parcel parcel);
}
