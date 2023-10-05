package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the first number:");
        double firstNumber = scanner.nextDouble();
        System.out.println("Enter the second number:");
        double secondNumber = scanner.nextDouble();



        System.out.println("What function would you like to use?");
        System.out.println("Add");
        System.out.println("Subtract");
        System.out.println("Multiply");
        System.out.println("Divide");
        System.out.println("A, S, M, or D?");
        scanner.nextLine();
        String functionUsed = scanner.nextLine();

        if (functionUsed.equalsIgnoreCase("A")) {
            double result = firstNumber + secondNumber;
            System.out.printf("%.2f + %.2f = %.2f", firstNumber, secondNumber, result);
        } else if (functionUsed.equalsIgnoreCase("S")) {
            double result = firstNumber - secondNumber;
            System.out.printf("%.2f - %.2f = %.2f", firstNumber, secondNumber, result);
        } else if (functionUsed.equalsIgnoreCase("M")) {
            double result = firstNumber * secondNumber;
            System.out.printf("%.2f * %.2f = %.2f", firstNumber, secondNumber, result);
        } else if (functionUsed.equalsIgnoreCase("D")) {
            double result = firstNumber / secondNumber;
            System.out.printf("%.2f / %.2f = %.2f", firstNumber, secondNumber, result);
        }

    }
}