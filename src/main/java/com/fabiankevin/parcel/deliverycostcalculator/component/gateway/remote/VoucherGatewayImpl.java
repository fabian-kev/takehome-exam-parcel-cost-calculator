package com.fabiankevin.parcel.deliverycostcalculator.component.gateway.remote;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.remote.response.VoucherEnvelope;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Voucher;
import com.fabiankevin.parcel.deliverycostcalculator.component.remoteservice.VoucherRemoteService;
import com.fabiankevin.parcel.deliverycostcalculator.component.util.LocalDateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherGatewayImpl implements VoucherGateway {
    private final VoucherRemoteService voucherRemoteService;
    @Override
    public Voucher getVoucher(String voucherCode) {
        VoucherEnvelope envelope = voucherRemoteService.getVoucher(voucherCode);
        return Voucher.builder()
                .code(envelope.getCode())
                .discount(envelope.getDiscount())
                .expiry(LocalDateUtil.parse(envelope.getExpiry(), "yyyy-MM-dd"))
                .build();
    }
}
