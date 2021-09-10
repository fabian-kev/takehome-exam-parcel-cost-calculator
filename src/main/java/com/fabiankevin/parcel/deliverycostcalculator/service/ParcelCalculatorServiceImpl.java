package com.fabiankevin.parcel.deliverycostcalculator.service;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.request.ParcelBody;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response.ParcelResponse;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Parcel;
import com.fabiankevin.parcel.deliverycostcalculator.interactor.ApplyVoucher;
import com.fabiankevin.parcel.deliverycostcalculator.interactor.CalculateDeliveryCost;
import com.fabiankevin.parcel.deliverycostcalculator.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParcelCalculatorServiceImpl implements ParcelCalculatorService {
    private final ParcelMapper parcelMapper;
    private final ApplyVoucher applyVoucher;
    private final CalculateDeliveryCost calculateDeliveryCost;
    @Override
    public ParcelResponse calculateDeliveryCost(ParcelBody parcelBody) {
        Parcel parcel = parcelMapper.toModel(parcelBody);
        Double price = calculateDeliveryCost.execute(parcel);

        return ParcelResponse.builder()
                .price(price)
                .build();
    }
}
