package com.fabiankevin.parcel.deliverycostcalculator.component.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiExceptionResource {
    private Integer status;
    private String message;
}
