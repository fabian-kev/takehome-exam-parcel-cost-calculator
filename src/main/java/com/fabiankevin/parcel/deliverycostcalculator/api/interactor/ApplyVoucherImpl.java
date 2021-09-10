package com.fabiankevin.parcel.deliverycostcalculator.api.interactor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplyVoucherImpl implements ApplyVoucher {
    @Override
    public Double execute(String voucherCode) {
        return null;
    }
}
