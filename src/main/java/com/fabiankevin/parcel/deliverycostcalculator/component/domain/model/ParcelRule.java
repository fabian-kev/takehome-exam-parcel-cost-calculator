package com.fabiankevin.parcel.deliverycostcalculator.component.domain.model;

import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleStatus;
import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParcelRule {
    private Long id;
    private String name;
    private Integer priority;
    private String description;
    private Double cost;
    private ParcelRuleStatus status;
    private ParcelRuleType type;
    private Double conditionValue;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
