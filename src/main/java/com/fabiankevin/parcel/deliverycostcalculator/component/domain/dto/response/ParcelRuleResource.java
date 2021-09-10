package com.fabiankevin.parcel.deliverycostcalculator.component.domain.dto.response;

import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleStatus;
import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParcelRuleResource {
    private Long id;
    private String name;
    private Integer priority;
    private String description;
    private Double cost;
    private ParcelRuleStatus status;
    private ParcelRuleType type;
    private Double conditionMin;
    private Double conditionMax;
}
