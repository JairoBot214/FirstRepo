package org.example;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TransactionManager tm = new TransactionManager();
        tm.readTransaction();
        boolean isMakingSelection = true;
        Scanner scanner = new Scanner(System.in);
        while (isMakingSelection) {
            System.out.printf("Hello! Welcome to the Accounting Ledger application.%n");
            System.out.println("Options:");
            System.out.println("A. Add a Deposit.");
            System.out.println("M. Make a Payment(Debit).");
            System.out.println("D. Display Ledger.");
            System.out.println("X. Exit Application.");
            System.out.println("Please enter a choice: ");

            char homeScanner = 'i';

            while (true) {
                try {
                    homeScanner = scanner.next().charAt(0);
                    homeScanner = Character.toUpperCase(homeScanner); // Convert input to uppercase
                    if (homeScanner == 'A' || homeScanner == 'M' || homeScanner == 'D' || homeScanner == 'X') {
                        break; // If a valid choice is selected, continue the app as normal; if invalid, the user will be asked for valid input
                    } else {
                        System.out.println("Please enter a valid choice: A, M, D, or X");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.next();
                }
            }

            switch (homeScanner) {
                case 'A':
                    tm.addDeposit(tm.transactionList);
                    break;
                case 'M':
                    tm.makePayment(tm.transactionList);
                    break;
                case 'D':
                    tm.displayLedger(tm.transactionList);
                    break;
                case 'X':
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}












