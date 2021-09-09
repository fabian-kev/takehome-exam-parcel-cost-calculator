package com.fabiankevin.parcel.deliverycostcalculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class DeliveryCostCalculatorApplicationTests {

    @Test
    void execute_then_computeparcel() {
        double weight = 5;//kg
        double height = 40;;//cm
        double width = 6;//cm
        double length = 6;//cm


        double volume = height * width * length;
        System.out.println("Volume: "+ volume);
        System.out.println(computeHeavyParcel(weight) );
        System.out.println( 0.3 * volume );

    }
    private double computeHeavyParcel(double weight){
        return 20 * weight;
    }

}
