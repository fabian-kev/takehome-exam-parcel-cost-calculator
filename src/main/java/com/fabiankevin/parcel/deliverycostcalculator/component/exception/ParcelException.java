package com.fabiankevin.parcel.deliverycostcalculator.component.exception;

public class ParcelException extends RuntimeException {
    public ParcelException(String message){
        super(message);
    }
    public static class ExceedMaximumWeightLimit extends ParcelException {
        public ExceedMaximumWeightLimit() {
            super("You've exceeded the maximum weight limit");
        }
    }
}
