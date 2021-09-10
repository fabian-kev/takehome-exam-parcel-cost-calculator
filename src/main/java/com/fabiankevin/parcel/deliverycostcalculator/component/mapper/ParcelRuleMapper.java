package com.fabiankevin.parcel.deliverycostcalculator.component.mapper;

import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response.ParcelResource;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response.ParcelRuleResource;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.entity.ParcelRuleEntity;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.model.ParcelRule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParcelRuleMapper {
    List<ParcelRule> toModels(List<ParcelRuleEntity> entityList);

    List<ParcelRuleResource> toResources(List<ParcelRule> parcelRules);
}
