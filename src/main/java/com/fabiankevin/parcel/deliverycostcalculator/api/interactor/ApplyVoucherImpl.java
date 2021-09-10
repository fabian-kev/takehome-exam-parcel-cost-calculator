package com.fabiankevin.parcel.deliverycostcalculator.api.interactor;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Voucher;
import com.fabiankevin.parcel.deliverycostcalculator.component.exception.VoucherException;
import com.fabiankevin.parcel.deliverycostcalculator.component.gateway.remote.VoucherGateway;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Slf4j
public class ApplyVoucherImpl implements ApplyVoucher {
    private final VoucherGateway voucherGateway;
    @Override
    public Double execute(Double price, String voucherCode) throws VoucherException {
        Voucher voucher;

        try {
            voucher = voucherGateway.getVoucher(voucherCode);
        } catch (FeignException e) {
            throw new VoucherException.VoucherNotExist();
        }
        if(LocalDate.now().isAfter(voucher.getExpiry())){
            throw new VoucherException.VoucherExpired();
        }

        log.info(voucherCode);
        log.info("Price: "+price);
        Double discount = voucher.getDiscount() / 100;
        log.info("Discount: "+discount);
        return price -= (price * discount);
    }
}
