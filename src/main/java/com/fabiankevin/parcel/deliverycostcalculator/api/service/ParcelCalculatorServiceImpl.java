package com.fabiankevin.parcel.deliverycostcalculator.api.service;

import com.fabiankevin.parcel.deliverycostcalculator.api.interactor.GetParcelRules;
import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleType;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.request.ParcelBody;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response.ParcelResponse;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.Parcel;
import com.fabiankevin.parcel.deliverycostcalculator.api.interactor.ApplyVoucher;
import com.fabiankevin.parcel.deliverycostcalculator.api.interactor.ComputeDeliveryCost;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.ParcelRule;
import com.fabiankevin.parcel.deliverycostcalculator.component.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParcelCalculatorServiceImpl implements ParcelCalculatorService {
    private final ParcelMapper parcelMapper;
    private final ApplyVoucher applyVoucher;
    private final GetParcelRules getParcelRules;

    @Override
    public ParcelResponse calculateDeliveryCost(ParcelBody parcelBody) {
        log.info(parcelBody.toString());
        Parcel parcel = parcelMapper.toModel(parcelBody);
        Double volume = parcel.getVolume().getVolumeValue();
        Double weight = parcel.getWeight();
        List<ParcelRule> parcelRules = getParcelRules.execute();
        log.info("Volume:" + volume);

        Double price = 0.0;


        for (ParcelRule rule : parcelRules) {
            if (rule.getPriority() == 1 && weight > rule.getConditionValue()) {
                throw new RuntimeException(String.format("You've exceeded the weight of %sKG", rule.getConditionValue()));
            }

            if (rule.getPriority() == 2) {
                if (weight > rule.getConditionValue()) {
                    System.out.println("weight");
                    price = rule.getCost() * weight;
                }
            }

            if (volume < rule.getConditionValue() && rule.getPriority() == 3) {
                System.out.println("PROR 3");

                price += rule.getCost() * volume;
            } else if (volume < rule.getConditionValue() && rule.getPriority() == 4) {
                System.out.println("PROR 4");

                price += rule.getCost() * volume;
            } else if(volume > rule.getConditionValue() && rule.getPriority() == 5) {
                System.out.println("PROR 5");
                price += rule.getCost() * volume;
            }

        }

        return ParcelResponse.builder()
                .price(price)
                .build();
    }
}
