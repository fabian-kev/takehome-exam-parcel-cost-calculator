package com.fabiankevin.parcel.deliverycostcalculator.component.remoteservice;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.remote.response.VoucherEnvelope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "voucher",
        url = "${voucher.baseUrl}")
public interface VoucherRemoteService {
    @GetMapping("/voucher/{voucherCode}?key=${voucher.apikey}")
    VoucherEnvelope getVoucher(@PathVariable("voucherCode") String voucherCode);
}
