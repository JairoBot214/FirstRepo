package org.example;

import java.time.LocalDate;

public class LeaseContract extends Contract{


    public double expectedEndingValue() {
            return getVehicleSold().getPrice() * .50;
        }

        public double leaseFee() {
            return getVehicleSold().getPrice() * .07;
        }


        public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
            super(LocalDate.parse(date), customerName,customerEmail,vehicleSold);
        }

        @Override
        public double getTotalPrice() {
            return (getMonthlyPayment() * 36);
        }

        @Override
        public double getMonthlyPayment() {
            return (getVehicleSold().getPrice() - expectedEndingValue() + leaseFee()) / 36 + ((getVehicleSold().getPrice() + expectedEndingValue() + leaseFee()) * (4 / 2400));
        }
    }
