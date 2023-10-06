package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the principal amount (P): ");
        double principal = scanner.nextDouble();

        System.out.print("Enter the annual interest rate (r)(in decimal form, e.g., 0.0175 for 1.75%): ");
        double annualInterestRate = scanner.nextDouble();

        System.out.print("Enter number in years (t): ");
        int years = scanner.nextInt();
        double interestRate =annualInterestRate /100;


        double dailyInterestRate = annualInterestRate / 365;
        int numberOfCompoundsPerYear = 365;



        double futureValue = principal * Math.pow(1 + interestRate/ numberOfCompoundsPerYear,
                numberOfCompoundsPerYear * years);

        double totalInterest = futureValue - principal;

        System.out.printf("Future Value: %.2f%n", futureValue);
        System.out.printf("Total Interest Earned: %.2f%n", totalInterest);
    }
}