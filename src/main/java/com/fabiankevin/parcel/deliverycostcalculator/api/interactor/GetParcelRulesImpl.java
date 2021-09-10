package com.fabiankevin.parcel.deliverycostcalculator.api.interactor;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.ParcelRule;
import com.fabiankevin.parcel.deliverycostcalculator.component.gateway.db.ParcelRuleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetParcelRulesImpl implements GetParcelRules {
    private final ParcelRuleGateway parcelRuleGateway;
    @Override
    public List<ParcelRule> execute() {
        return parcelRuleGateway.getParcelRules().stream()
                .sorted(Comparator.comparing(ParcelRule::getPriority))
                .collect(Collectors.toList());
    }
}
