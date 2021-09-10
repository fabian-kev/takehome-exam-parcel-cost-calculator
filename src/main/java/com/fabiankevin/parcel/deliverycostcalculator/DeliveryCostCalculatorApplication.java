package com.fabiankevin.parcel.deliverycostcalculator;

import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleStatus;
import com.fabiankevin.parcel.deliverycostcalculator.component.constant.ParcelRuleType;
import com.fabiankevin.parcel.deliverycostcalculator.component.domain.entity.ParcelRuleEntity;
import com.fabiankevin.parcel.deliverycostcalculator.component.repository.ParcelRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class DeliveryCostCalculatorApplication {
    @Autowired
    private ParcelRuleRepository parcelRuleRepository;
    public static void main(String[] args) {
        SpringApplication.run(DeliveryCostCalculatorApplication.class, args);
    }


    @PostConstruct
    public void loadInitialParcelRules() {
        List<ParcelRuleEntity> rules = Arrays.asList(
                ParcelRuleEntity.builder()
                        .cost(0.0)
                        .description("Weight exceeds 50kg")
                        .name("Reject")
                        .priority(1)
                        .status(ParcelRuleStatus.ACTIVE)
                        .type(ParcelRuleType.WEIGHT_VALIDATION)
                        .conditionValue(50.0)
                        .build(),

                ParcelRuleEntity.builder()
                        .cost(20.0)
                        .description("Weight exceeds 10kg")
                        .name("Heavy Parcel")
                        .priority(2)
                        .status(ParcelRuleStatus.ACTIVE)
                        .type(ParcelRuleType.WEIGHT)
                        .conditionValue(10.0)
                        .build(),

                ParcelRuleEntity.builder()
                        .cost(0.03)
                        .description("Volume is less than 1500 cm3")
                        .name("Small Parcel")
                        .priority(3)
                        .status(ParcelRuleStatus.ACTIVE)
                        .type(ParcelRuleType.VOLUME)
                        .conditionValue(1500.0)
                        .build(),

                ParcelRuleEntity.builder()
                        .cost(0.04)
                        .description("Volume is less than 2500 cm3")
                        .name("Medium")
                        .priority(4)
                        .status(ParcelRuleStatus.ACTIVE)
                        .type(ParcelRuleType.VOLUME)
                        .conditionValue(2500.0)
                        .build(),

                ParcelRuleEntity.builder()
                        .cost(0.05)
                        .description("Greater than 2500 cm3")
                        .name("Large Parcel")
                        .priority(5)
                        .status(ParcelRuleStatus.ACTIVE)
                        .type(ParcelRuleType.VOLUME)
                        .conditionValue(2500.0)
                        .build()
        );

        parcelRuleRepository.saveAll(rules);
    }

}
