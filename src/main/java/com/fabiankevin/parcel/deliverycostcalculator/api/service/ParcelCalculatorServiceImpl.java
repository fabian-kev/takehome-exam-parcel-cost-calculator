package com.fabiankevin.parcel.deliverycostcalculator.api.service;

import com.fabiankevin.parcel.deliverycostcalculator.api.interactor.GetParcelRules;
import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleType;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.request.ParcelForm;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response.ParcelResource;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Parcel;
import com.fabiankevin.parcel.deliverycostcalculator.api.interactor.ApplyVoucher;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.ParcelRule;
import com.fabiankevin.parcel.deliverycostcalculator.component.exception.ParcelException;
import com.fabiankevin.parcel.deliverycostcalculator.component.exception.VoucherException;
import com.fabiankevin.parcel.deliverycostcalculator.component.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParcelCalculatorServiceImpl implements ParcelCalculatorService {
    private final ParcelMapper parcelMapper;
    private final ApplyVoucher applyVoucher;
    private final GetParcelRules getParcelRules;

    @Override
    public ParcelResource computeDeliveryCost(ParcelForm parcelForm) {
        log.info(parcelForm.toString());
        Parcel parcel = parcelMapper.toModel(parcelForm);

        Double price = computeCostBasedOnRules(parcel);

        if(StringUtils.isNotEmpty(parcel.getVoucherCode()) ){
            try {
                price = applyVoucher.execute(price, parcel.getVoucherCode());
            } catch (VoucherException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return ParcelResource.builder()
                .price(price)
                .build();
    }
    private Double computeCostBasedOnRules(Parcel parcel){
        log.info("Parcel: "+parcel);
        List<ParcelRule> parcelRules = getParcelRules.execute();
        Double volume = parcel.getVolume().getVolumeValue();
        Double weight = parcel.getWeight();
        Double price = 0.0;
        for (ParcelRule rule : parcelRules) {
            if (rule.getType() == ParcelRuleType.WEIGHT_VALIDATION) {
                if( weight > rule.getConditionMin() ){
                    throw new ParcelException.ExceedMaximumWeightLimit();
                }
            }
            if (rule.getType() == ParcelRuleType.WEIGHT) {
                if (weight > rule.getConditionMin()) {
                    price = rule.getCost() * weight;
                }
            }
            if( rule.getType() == ParcelRuleType.VOLUME ){
                if(volume >= rule.getConditionMin() && volume < rule.getConditionMax() ){
                    price += rule.getCost() * volume;
                }
            }
        }

        return price;
    }
}
