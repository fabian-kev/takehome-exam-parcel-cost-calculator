package com.fabiankevin.parcel.deliverycostcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DeliveryCostCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryCostCalculatorApplication.class, args);
    }

}
