package com.fabiankevin.parcel.deliverycostcalculator.service;

import com.fabiankevin.parcel.deliverycostcalculator.component.parcel.gateway.domain.dto.request.ParcelBody;
import com.fabiankevin.parcel.deliverycostcalculator.component.parcel.gateway.domain.dto.response.ParcelResponse;

public interface ParcelCalculatorService {
    ParcelResponse calculateDeliveryCost(ParcelBody parcelBody);
}
