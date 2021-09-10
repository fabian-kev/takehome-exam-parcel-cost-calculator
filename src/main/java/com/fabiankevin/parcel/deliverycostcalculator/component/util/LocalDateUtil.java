package com.fabiankevin.parcel.deliverycostcalculator.component.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class LocalDateUtil {

    public static LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(
                localDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
    }


    public static LocalDate parse(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static Date fromStringDateToDate(String date, String pattern) {
        return toDate(LocalDateUtil.parse(date, pattern));
    }

}
