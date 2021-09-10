package com.fabiankevin.parcel.deliverycostcalculator.component.gateway.remote;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Voucher;

public interface VoucherGateway {
    Voucher getVoucher(String voucherCode);
}
