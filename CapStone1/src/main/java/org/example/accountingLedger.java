package org.example;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class accountingLedger {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Ledger(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    public String toString() {
        return String.format("Date: %s, Time: %s, Description: %s, Vendor: %s, Amount: %.2f",
                date, time, description, vendor, amount);}

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    static void displayLedger(ArrayList<Ledger> transactionList) {
        Scanner scanner = new Scanner(System.in);
        boolean isMakingSelection = true;

        while (isMakingSelection) {
            System.out.println("Welcome to the Ledger Menu.");
            System.out.println("1. Display all entries.");
            System.out.println("2. Display Deposits.");
            System.out.println("3. Display Payments.");
            System.out.println("4. Run Report.");
            System.out.println("5. Go back to the home screen.");
            System.out.print("Please enter a number: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAllEntries(transactionList);
                    break;
                case 2:
                    displayDeposits(transactionList);
                    break;
                case 3:
                    displayPayments(transactionList);
                    break;
                case 4:
                    runReport(transactionList);
                    break;
                case 5:
                    isMakingSelection = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
    static void displayAllEntries(ArrayList<Ledger> transactionList) {
        // Sort the transactionList by date in descending order (newest to oldest)
        Collections.sort(transactionList, Comparator.comparing(Ledger::getDate).reversed());
        // Display all entries from transactionList
        for (Ledger transaction : transactionList) {
            System.out.println(transaction);
        }
    }

    static void displayDeposits(ArrayList<Ledger> transactionList) {
        // Sort again newest to oldest
        Collections.sort(transactionList, Comparator.comparing(Ledger::getDate).reversed());
        // Display deposits only
        for (Ledger transaction : transactionList) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }

    static void displayPayments(ArrayList<Ledger> transactionList) {
        // Sort again newest to oldest
        Collections.sort(transactionList, Comparator.comparing(Ledger::getDate).reversed());
        // Display payments or negative entries only
        for (Ledger transaction : transactionList) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
    }
    static void runReport(ArrayList<Ledger> transactionList) {
        // report method here
    }
}


