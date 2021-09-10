package com.fabiankevin.parcel.deliverycostcalculator.controller;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.request.ParcelBody;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response.ParcelResponse;
import com.fabiankevin.parcel.deliverycostcalculator.service.ParcelCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("parcel")
@RequiredArgsConstructor
public class ParcelController {

    private final ParcelCalculatorService parcelCalculatorService;

    @PostMapping("/cost-calculator")
    ParcelResponse calculateDeliveryCost(@RequestBody ParcelBody parcelBody){
        return parcelCalculatorService.calculateDeliveryCost(parcelBody);
    }
}
