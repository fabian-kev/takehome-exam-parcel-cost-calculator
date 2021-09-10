package com.fabiankevin.parcel.deliverycostcalculator.api.interactor;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.ParcelRule;

import java.util.List;

public interface GetParcelRules {
    List<ParcelRule> execute();
}
