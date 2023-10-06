package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the monthly payout amount (PMT): ");
        double monthlyPayout = scanner.nextDouble();

        System.out.print("Enter the annual interest rate (r): ");
        double annualInterestRate = scanner.nextDouble();

        System.out.print("Enter the number of years to pay out (t): ");
        int years = scanner.nextInt();



        double monthlyInterestRate = (annualInterestRate /12 /100);
        int numberOfPayments = years * 12;


        double presentValue = monthlyPayout * (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments)) / monthlyInterestRate;

        System.out.printf("Present Value of the Annuity: %.2f%n", presentValue);

    }
}