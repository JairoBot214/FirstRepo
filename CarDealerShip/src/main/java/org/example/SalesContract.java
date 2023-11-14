package org.example;

import java.time.LocalDate;

public class SalesContract extends Contract{

    private boolean isFinanced;
    public SalesContract(LocalDate dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
    }

    public String isFinanced() {
        if(isFinanced){
            return "YES";
        }
        else{
            return "NO";
        }
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    public double salesTaxAmount(){
        return getVehicleSold().getPrice()*0.05;
    }
    public double recordingFee(){
        return 100;
    }
    public double processingFee(){
        if (getVehicleSold().getPrice()<=10000){
            return 295;
        }
        else{
            return 495;
        }
    }
    @Override
    public double getTotalPrice() {
        if(isFinanced) {
            double P = getVehicleSold().getPrice();//principal loan amount
            double r = 0.0425/12;//monthly interest rate
            double R = 0.0525/12;//monthly interest rate
            if(P>=10000){
                return (48*(P*r*Math.pow(1+r,48))/(Math.pow(1+r,48)-1))+ recordingFee() + processingFee() + salesTaxAmount();
            }
            else {
                return (24*(P*R*Math.pow(1+R,24))/(Math.pow(1+R,24)-1))+ recordingFee() + processingFee() + salesTaxAmount();
            }

        }
        else{
            return getVehicleSold().getPrice() + recordingFee() + processingFee() + salesTaxAmount();

        }

    }

    @Override
    public double getMonthlyPayment() {
        double P = getVehicleSold().getPrice();//principal loan amount
        double r = 0.0425/12;//monthly interest rate
        double R = 0.0525/12;//monthly interest rate
        if(isFinanced && P>=10000){
            return (P*r*Math.pow(1+r,48))/(Math.pow(1+r,48)-1);
        }
        else if(isFinanced && P<10000){
            return (P*R*Math.pow(1+R,24))/(Math.pow(1+R,24)-1);
        }
        else{
            return 0;
        }

    }
}