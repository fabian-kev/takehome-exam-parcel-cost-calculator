package com.fabiankevin.parcel.deliverycostcalculator.component.gateway.db;

import com.fabiankevin.parcel.deliverycostcalculator.component.mapper.ParcelRuleMapper;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.ParcelRule;
import com.fabiankevin.parcel.deliverycostcalculator.component.repository.ParcelRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelRuleGatewayImpl implements ParcelRuleGateway {
    private final ParcelRuleRepository parcelRuleRepository;
    private final ParcelRuleMapper parcelRuleMapper;
    @Override
    public List<ParcelRule> getParcelRules() {
        return parcelRuleMapper.toModels(parcelRuleRepository.findAll());
    }
}
