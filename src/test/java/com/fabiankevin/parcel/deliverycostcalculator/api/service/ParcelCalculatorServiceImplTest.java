package com.fabiankevin.parcel.deliverycostcalculator.api.service;

import com.fabiankevin.parcel.deliverycostcalculator.api.interactor.ApplyVoucher;
import com.fabiankevin.parcel.deliverycostcalculator.api.interactor.GetParcelRules;
import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleStatus;
import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleType;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.request.ParcelForm;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response.ParcelResource;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.ParcelRule;
import com.fabiankevin.parcel.deliverycostcalculator.component.exception.VoucherException;
import com.fabiankevin.parcel.deliverycostcalculator.component.mapper.ParcelMapperImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;

@ExtendWith(MockitoExtension.class)
class ParcelCalculatorServiceImplTest {

    @Mock
    private ApplyVoucher applyVoucher;

    @Mock
    private GetParcelRules getParcelRules;


    private ParcelCalculatorService parcelCalculatorService;


    @BeforeEach
    public void init(){
        parcelCalculatorService = new ParcelCalculatorServiceImpl(
                new ParcelMapperImpl(),
                applyVoucher,
                getParcelRules
        );


        List<ParcelRule> rules = Arrays.asList(
                ParcelRule.builder()
                        .cost(0.0)
                        .description("Weight exceeds 50kg")
                        .name("Reject")
                        .priority(1)
                        .status(ParcelRuleStatus.ACTIVE)
                        .type(ParcelRuleType.WEIGHT_VALIDATION)
                        .conditionMin(50.0)
                        .conditionMax(Double.MAX_VALUE)
                        .build(),

                ParcelRule.builder()
                        .cost(20.0)
                        .description("Weight exceeds 10kg")
                        .name("Heavy Parcel")
                        .priority(2)
                        .status(ParcelRuleStatus.ACTIVE)
                        .type(ParcelRuleType.WEIGHT)
                        .conditionMin(10.0)
                        .conditionMax(Double.MAX_VALUE)
                        .build(),

                ParcelRule.builder()
                        .cost(0.03)
                        .description("Volume is less than 1500 cm3")
                        .name("Small Parcel")
                        .priority(3)
                        .status(ParcelRuleStatus.ACTIVE)
                        .type(ParcelRuleType.VOLUME)
                        .conditionMin(0.0)
                        .conditionMax(1500.0)
                        .build(),

                ParcelRule.builder()
                        .cost(0.04)
                        .description("Volume is less than 2500 cm3")
                        .name("Medium")
                        .priority(4)
                        .status(ParcelRuleStatus.ACTIVE)
                        .type(ParcelRuleType.VOLUME)
                        .conditionMin(1500.0)
                        .conditionMax(2500.0)
                        .build(),

                ParcelRule.builder()
                        .cost(0.05)
                        .description("Greater than 2500 cm3")
                        .name("Large Parcel")
                        .priority(5)
                        .status(ParcelRuleStatus.ACTIVE)
                        .type(ParcelRuleType.VOLUME)
                        .conditionMin(2500.0)
                        .conditionMax(Double.MAX_VALUE)
                        .build()
        );

        Mockito.when(getParcelRules.execute())
                .thenReturn(rules);
    }

    @Test
    public void computeDeliveryCost_whenAllValid_shouldEqualTheExpectedPrice() throws VoucherException {
        double expectedPrice = 259.93;
        ParcelForm parcelForm = new ParcelForm();
        parcelForm.setWeight(11.0);
        ParcelForm.Volume volume = new ParcelForm.Volume();
        volume.setHeight(11.0);
        volume.setLength(11.0);
        volume.setWidth(11.0);
        parcelForm.setVolume(volume);


        ParcelResource resource = parcelCalculatorService.computeDeliveryCost(parcelForm);
        Assertions.assertThat(resource.getPrice()).isEqualTo(expectedPrice);
        Mockito.verify(applyVoucher, Mockito.times(0))
                .execute(anyDouble(), any());
    }

    @Test
    public void computeDeliveryCost_whenAllValidAndHasValidVoucher_shouldEqualToExpectedDiscountedPrice() throws VoucherException {
        double expectedDiscountedPricePrice = 37.93;//
        ParcelForm parcelForm = new ParcelForm();
        parcelForm.setWeight(10.0);
        ParcelForm.Volume volume = new ParcelForm.Volume();
        volume.setHeight(11.0);
        volume.setLength(11.0);
        volume.setWidth(11.0);
        parcelForm.setVolume(volume);
        parcelForm.setVoucherCode("AAAA");

        Mockito.when(applyVoucher.execute(any(Double.class), any(String.class)))
                .thenReturn(expectedDiscountedPricePrice);

        ParcelResource resource = parcelCalculatorService.computeDeliveryCost(parcelForm);
        Assertions.assertThat(resource.getPrice()).isEqualTo(expectedDiscountedPricePrice);
        Mockito.verify(applyVoucher, Mockito.times(1))
                .execute(any(), any());
    }

    @Test
    public void computeDeliveryCost_whenVoucherIsNotFound_thenThrowRuntimeException() throws VoucherException {
        ParcelForm parcelForm = new ParcelForm();
        parcelForm.setWeight(10.0);
        ParcelForm.Volume volume = new ParcelForm.Volume();
        volume.setHeight(11.0);
        volume.setLength(11.0);
        volume.setWidth(11.0);
        parcelForm.setVolume(volume);
        parcelForm.setVoucherCode("AAAA");

        Mockito.when(applyVoucher.execute(any(Double.class), any(String.class)))
                .thenThrow(VoucherException.VoucherNotExist.class);

        assertThrows(RuntimeException.class, () -> {
            parcelCalculatorService.computeDeliveryCost(parcelForm);
        });

    }

    @Test
    public void computeDeliveryCost_whenVoucherIsExpired_thenThrowRuntimeException() throws VoucherException {
        ParcelForm parcelForm = new ParcelForm();
        parcelForm.setWeight(10.0);
        ParcelForm.Volume volume = new ParcelForm.Volume();
        volume.setHeight(11.0);
        volume.setLength(11.0);
        volume.setWidth(11.0);
        parcelForm.setVolume(volume);
        parcelForm.setVoucherCode("AAAA");

        Mockito.when(applyVoucher.execute(any(Double.class), any(String.class)))
                .thenThrow(VoucherException.VoucherExpired.class);

        assertThrows(RuntimeException.class, () -> {
            parcelCalculatorService.computeDeliveryCost(parcelForm);
        });

    }
}