package com.electricity.calculator.util;

public class BillCalculator {
    public static double calculateBill(int units) {
        double bill = 0;
        if (units <= 50) {
            bill = units * 3.5;
        } else if (units <= 150) {
            bill = (50 * 3.5) + ((units - 50) * 4);
        } else if (units <= 250) {
            bill = (50 * 3.5) + (100 * 4) + ((units - 150) * 5.2);
        } else {
            bill = (50 * 3.5) + (100 * 4) + (100 * 5.2) + ((units - 250) * 6.5);
        }
        return bill;
    }
}
