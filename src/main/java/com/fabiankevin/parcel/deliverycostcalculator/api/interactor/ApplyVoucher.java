package com.fabiankevin.parcel.deliverycostcalculator.api.interactor;

import com.fabiankevin.parcel.deliverycostcalculator.component.exception.VoucherException;

public interface ApplyVoucher {
    Double execute(Double price, String voucherCode) throws VoucherException;
}
