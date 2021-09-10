package com.fabiankevin.parcel.deliverycostcalculator.component.gateway.db;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.ParcelRule;

import java.util.List;

public interface ParcelRuleGateway {
    List<ParcelRule> getParcelRules();
}
