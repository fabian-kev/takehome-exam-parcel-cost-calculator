package com.fabiankevin.parcel.deliverycostcalculator.api.service;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.request.ParcelForm;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response.ParcelResource;

public interface ParcelCalculatorService {
    ParcelResource computeDeliveryCost(ParcelForm parcelForm);
}
