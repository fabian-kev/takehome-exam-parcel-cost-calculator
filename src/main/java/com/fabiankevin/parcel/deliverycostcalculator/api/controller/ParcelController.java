package com.fabiankevin.parcel.deliverycostcalculator.api.controller;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.request.ParcelBody;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response.ParcelResource;
import com.fabiankevin.parcel.deliverycostcalculator.api.service.ParcelCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("parcel")
@RequiredArgsConstructor
public class ParcelController {

    private final ParcelCalculatorService parcelCalculatorService;

    @PostMapping("/cost-calculator")
    ParcelResource calculateDeliveryCost(@Valid @RequestBody ParcelBody parcelBody){
        return parcelCalculatorService.computeDeliveryCost(parcelBody);
    }
}
