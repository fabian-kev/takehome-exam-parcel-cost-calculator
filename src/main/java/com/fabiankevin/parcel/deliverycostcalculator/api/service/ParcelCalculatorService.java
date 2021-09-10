package com.fabiankevin.parcel.deliverycostcalculator.api.service;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.request.ParcelBody;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response.ParcelResponse;

public interface ParcelCalculatorService {
    ParcelResponse calculateDeliveryCost(ParcelBody parcelBody);
}
