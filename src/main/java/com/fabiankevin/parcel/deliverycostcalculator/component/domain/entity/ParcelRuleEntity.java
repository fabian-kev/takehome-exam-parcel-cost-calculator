package com.fabiankevin.parcel.deliverycostcalculator.component.domain.entity;

import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleStatus;
import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PARCEL_RULE")
public class ParcelRuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer priority;
    private String description;
    private Double cost;
    private ParcelRuleStatus status;
    private ParcelRuleType type;
    private Double conditionMin;
    private Double conditionMax;
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime modifiedDate = LocalDateTime.now();


}
