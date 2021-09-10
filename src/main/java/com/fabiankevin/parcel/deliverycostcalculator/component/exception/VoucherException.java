package com.fabiankevin.parcel.deliverycostcalculator.component.exception;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Voucher;

public class VoucherException extends Exception {
    public VoucherException(String message){
        super(message);
    }

    public static class VoucherNotExist extends VoucherException {
        public VoucherNotExist() {
            super("Your voucher doesn't exist");
        }
    }
    public static class VoucherExpired extends VoucherException {
        public VoucherExpired() {
            super("Your voucher is expired");
        }
    }

}
