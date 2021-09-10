package com.fabiankevin.parcel.deliverycostcalculator.api.interactor;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Voucher;
import com.fabiankevin.parcel.deliverycostcalculator.component.exception.VoucherException;
import com.fabiankevin.parcel.deliverycostcalculator.component.gateway.remote.VoucherGateway;
import feign.FeignException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ApplyVoucherImplTest {

    @Mock
    private VoucherGateway voucherGateway;

    @InjectMocks
    private ApplyVoucherImpl applyVoucher;


    @Test
    public void execute_whenVoucherCodeValid_thenReturnDiscountedPrice(){
        double price = 1000;
        double expectedDiscountedPrice = 900;
        String voucher = "AAAA";
        Mockito.when(voucherGateway.getVoucher(ArgumentMatchers.eq(voucher)))
                .thenReturn(Voucher.builder()
                        .expiry(LocalDate.now().plusMonths(1))
                        .code(voucher)
                        .discount(10.0)
                        .build());
        try {
            Double discountedPrice = applyVoucher.execute(price, voucher);
            Assertions.assertThat(expectedDiscountedPrice).isEqualTo(discountedPrice);
        } catch (VoucherException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("On execute when voucher code is expired then throw voucher code expired exception")
    public void execute_whenVoucherCodeIsExpired_thenThrowException(){
        double price = 1000;
        String voucher = "AAAA";
        Mockito.when(voucherGateway.getVoucher(ArgumentMatchers.eq(voucher)))
                .thenReturn(Voucher.builder()
                        .expiry(LocalDate.now().minusMonths(1))
                        .code(voucher)
                        .discount(10.0)
                        .build());

        assertThrows(VoucherException.VoucherExpired.class, () -> {
            applyVoucher.execute(price, voucher);
        });
    }

    @Test
    @DisplayName("On execute when voucher code is not found then throw voucher code not found exception")
    public void execute_whenVoucherCodeIsNotFound_thenThrowException(){
        double price = 1000;
        String voucher = "AAAA";
        Mockito.when(voucherGateway.getVoucher(ArgumentMatchers.eq(voucher)))
                .thenThrow(FeignException.class);

        assertThrows(VoucherException.VoucherNotExist.class, () -> {
            applyVoucher.execute(price, voucher);
        });
    }
}