package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static DateTimeFormatter timeFormatter;

    public static void main(String[] args) {
        ArrayList<Ledger> transactionList = new ArrayList<>();

        //setting date and time formats
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

        try {
            FileInputStream fis = new FileInputStream("src/main/resources/transactions.csv");
            Scanner scanner = new Scanner(fis);
            scanner.nextLine(); // Skip the header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] transaction = line.split("\\|");

                // Parse date and time
                LocalDate date = LocalDate.parse(transaction[0], dateFormatter);
                LocalTime time = LocalTime.parse(transaction[1], timeFormatter);
                String description = transaction[2];
                String vendor = transaction[3];
                double amount = Double.parseDouble(transaction[4]);

                Ledger newTransaction = new Ledger(date, time, description, vendor, amount);
                transactionList.add(newTransaction);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Your file is not there");
        }

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
                    addDeposit(transactionList);
                    break;
                case 'M':
                    makePayment(transactionList);
                    break;
                case 'D':
                    report(transactionList);
                    break;
                case 'X':
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void addDeposit(ArrayList<Ledger> transactionList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the following deposit information:");
        System.out.print("Enter the date (MM-DD-YYYY): ");
        LocalDate dateInput = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("mm-DD-yyyy"));
        scanner.nextLine();
        System.out.print("Enter the time (Hour:Minute AM/PM): ");
        LocalTime timeInput = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("h:mm a"));
        System.out.println("Enter a description: ");
        String userDescription = scanner.nextLine().trim();
        System.out.println("Enter the vendor name: ");
        String vendorName = scanner.nextLine();
        System.out.println("Enter an amount: ");
        double depositAmount = scanner.nextDouble();

        Ledger newTransaction = new Ledger(dateInput, timeInput, userDescription, vendorName, depositAmount);
        transactionList.add(newTransaction);
        saveTransactionToCSV(newTransaction);
        System.out.println("Deposit successfully added. \n");
    }

    private static void saveTransactionToCSV(Ledger newTransaction) {
        try {
            File file = new File("src/main/resources/transactions.csv");


            // If the file doesn't exist, write the header
            if (!file.exists()) {
                FileWriter newTransactionWriter = new FileWriter("src/main/resources/transactions.csv");
                newTransactionWriter.write("Date|Time|Description|Vendor|Amount\n");
                newTransactionWriter.close();
            }

            try (FileWriter appendTransactionWriter = new FileWriter("src/main/resources/transactions.csv", true)) {
                appendTransactionWriter.write(
                        newTransaction.getDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + "|" +
                                newTransaction.getTime() + "|" +
                                newTransaction.getDescription() + "|" +
                                newTransaction.getVendor() + "|" +
                                newTransaction.getAmount() + "\n"
                );
                appendTransactionWriter.close();
            }

        } catch (IOException ex) {
            System.out.println("There's a problem with saving the transaction to CSV.");
        }

    }

    static void makePayment(ArrayList<Ledger> transactionList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the following payment information:");

        System.out.print("Enter the date (DD-MM-YYYY): ");
        LocalDate dateInput = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        System.out.print("Enter the time (Hour:Minute AM/PM): ");
        String timeInputString = scanner.next() + " " + scanner.next();
        LocalTime timeInput = LocalTime.parse(timeInputString, DateTimeFormatter.ofPattern("h:mm a"));

        scanner.nextLine(); // Consume the newline character after reading time

        System.out.print("Enter a description: ");
        String userDescription = scanner.nextLine().trim();

        System.out.print("Enter the vendor name: ");
        String vendorName = scanner.nextLine().trim();

        System.out.print("Enter an amount: ");
        double paymentAmount = scanner.nextDouble();

        // Consume the remaining newline character
        scanner.nextLine();

        Ledger newTransaction = new Ledger(dateInput, timeInput, userDescription, vendorName, -paymentAmount);
        transactionList.add(newTransaction);
        saveTransactionToCSV(newTransaction);
        System.out.println("Payment successfully added.\n");
    }

    // Your other methods and classes...
    static void report(ArrayList<Ledger> transactionList) {
        Scanner scanner = new Scanner(System.in);
        boolean isMakingSelection = true;

        while (isMakingSelection) {
            System.out.println("Welcome to the Report Screen.");
            System.out.println("1. Month to Date.");
            System.out.println("2. Previous Month.");
            System.out.println("3. Year to Date.");
            System.out.println("4. Previous Year.");
            System.out.println("5. Search by Vendor.");
            System.out.println("6. Custom Search.");
            System.out.println("7. Back.");
            System.out.print("Please enter a number: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    monthToDate(transactionList);
                    break;
                case 2:
                    previousMonth(transactionList);
                    break;
                case 3:
                    yearToDate(transactionList);
                    break;
                case 4:
                    previousYear(transactionList);
                    break;
                case 5:
                    searchByVendor(transactionList);
                    break;
                case 6:
                    customSearch(transactionList);
                    break;
                case 7:
                    isMakingSelection = false;
                    break;
            }
        }
    }

    static void monthToDate(ArrayList<Ledger> transactionList) {
        LocalDate currentDate = LocalDate.now();
        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            if(YearMonth.now().equals(YearMonth.from(transaction.getDate()))){
                filteredTransactions.add(transaction);
            }
            System.out.println(filteredTransactions);
        }

    }

    static List<Ledger> previousMonth(ArrayList<Ledger> transactionList) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDateOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate endDateOfPreviousMonth = currentDate.withDayOfMonth(1).minusDays(1);

        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            LocalDate transactionDate = transaction.getDate();
            if (transactionDate.isAfter(startDateOfPreviousMonth) && transactionDate.isBefore(endDateOfPreviousMonth)) {
                filteredTransactions.add(transaction);

            }

        }
        return filteredTransactions;
    }

    static void yearToDate(ArrayList<Ledger> transactionList) {
        LocalDate currentDate = LocalDate.now();
        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            if (transaction.getDate().getYear() == currentDate.getYear()) {
                filteredTransactions.add(transaction);
            }
        }
    }

    static void previousYear(ArrayList<Ledger> transactionList) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDateOfPreviousYear = currentDate.minusYears(1).withDayOfYear(1);
        LocalDate endDateOfPreviousYear = currentDate.withDayOfYear(1).minusDays(1);

        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            LocalDate transactionDate = transaction.getDate();
            if (transactionDate.isAfter(startDateOfPreviousYear) && transactionDate.isBefore(endDateOfPreviousYear)) {
                filteredTransactions.add(transaction);
            }
        }
    }

    static void searchByVendor(ArrayList<Ledger> transactionList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the vendor name: ");
        String vendorName = scanner.nextLine();

        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                filteredTransactions.add(transaction);
            }
        }
    }

    static void customSearch(ArrayList<Ledger> transactions) {
        Scanner scanner = new Scanner(System.in);
        LocalDate startDate = null;
        LocalDate endDate = null;
        String description;
        String vendor;
        double amount = -1;

        // Search prompts

        System.out.print("Enter start date (yyyy-MM-dd) or leave blank: ");
        String startDateInput = scanner.nextLine();
        if (!startDateInput.isEmpty()) {
            startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        System.out.print("Enter end date (yyyy-MM-dd) or leave blank: ");
        String endDateInput = scanner.nextLine();
        if (!endDateInput.isEmpty()) {
            endDate = LocalDate.parse(endDateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        System.out.print("Enter description or leave blank: ");
        description = scanner.nextLine();

        System.out.print("Enter vendor or leave blank: ");
        vendor = scanner.nextLine();

        System.out.print("Enter amount or leave blank: ");
        String amountInput = scanner.nextLine();
        if (!amountInput.isEmpty()) {
            try {
                amount = Double.parseDouble(amountInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for amount. Please enter a valid number.");
                // Handle the exception as per your application's requirements
            }
        }
    }
}







