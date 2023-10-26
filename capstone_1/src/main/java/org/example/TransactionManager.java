package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransactionManager {
    ArrayList<Ledger> transactionList = new ArrayList<>();

    public void readTransaction() {


        //setting date and time formats

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

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
    }

    public void addDeposit(ArrayList<Ledger> transactionList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the following deposit information:");
        System.out.print("Enter the date (mm-DD-YYYY): ");
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

    private void saveTransactionToCSV(Ledger newTransaction) {
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

    public void makePayment(ArrayList<Ledger> transactionList) {
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

    public void monthToDate(ArrayList<Ledger> transactionList) {
        LocalDate currentDate = LocalDate.now();
        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            if (YearMonth.now().equals(YearMonth.from(transaction.getDate()))) {
                filteredTransactions.add(transaction);
            }
            System.out.println(filteredTransactions);
        }

    }

    public List<Ledger> previousMonth(ArrayList<Ledger> transactionList) {
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

    public void yearToDate(ArrayList<Ledger> transactionList) {
        LocalDate currentDate = LocalDate.now();
        List<Ledger> filteredTransactions = new ArrayList<>();

        for (Ledger transaction : transactionList) {
            if (transaction.getDate().getYear() == currentDate.getYear()) {
                filteredTransactions.add(transaction);
            }
        }
    }

    public void previousYear(ArrayList<Ledger> transactionList) {
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

    public void searchByVendor(ArrayList<Ledger> transactionList) {
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

    public void displayAllEntries(ArrayList<Ledger> transactionList) {
        // Sort the transactionList by date in descending order (newest to oldest)
        Collections.sort(transactionList, Comparator.comparing(Ledger::getDate).reversed());
        // Display all entries from transactionList
        for (Ledger transaction : transactionList) {
            System.out.println(transaction);
        }


    }


    public void displayDeposits(ArrayList<Ledger> transactionList) {
        // Sort again newest to oldest
        Collections.sort(transactionList, Comparator.comparing(Ledger::getDate).reversed());
        // Display deposits only
        for (Ledger transaction : transactionList) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }

    public void displayPayments(ArrayList<Ledger> transactionList) {
        // Sort again newest to oldest
        Collections.sort(transactionList, Comparator.comparing(Ledger::getDate).reversed());
        // Display payments or negative entries only
        for (Ledger transaction : transactionList) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
    }

    public void displayLedger(ArrayList<Ledger> transactionList) {
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
                    report(transactionList);
                    break;
                case 5:
                    isMakingSelection = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public void report(ArrayList<Ledger> transactionList) {
        Scanner scanner = new Scanner(System.in);
        boolean isMakingSelection = true;

        while (isMakingSelection) {
            System.out.println("Welcome to the Report Screen.");
            System.out.println("1. Month to Date.");
            System.out.println("2. Previous Month.");
            System.out.println("3. Year to Date.");
            System.out.println("4. Previous Year.");
            System.out.println("5. Search by Vendor.");
            System.out.println("6. Back.");
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
                    isMakingSelection = false;
                    break;

            }
        }


    }
}