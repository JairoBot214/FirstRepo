package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter pick-up date (MM-DD-YYYY):");
        String pickupDate = scanner.nextLine();



        System.out.println("How many days would you like to rent the car?");
        int rentalDays = scanner.nextInt();
        System.out.println("How old are you?");
        int age = scanner.nextInt();


        System.out.println("Do you want a toll tag? (Y/N):");
        boolean tollTag = scanner.next().equalsIgnoreCase("Y");
        System.out.println("Do you want roadside assistance? (Y/N)");
        boolean roadsideAssistance = scanner.next().equalsIgnoreCase("Y");
        System.out.println("Do you want GPS?(Y/N) ");
        boolean gps = scanner.next().equalsIgnoreCase("Y");

        double basicRentalCost = 29.99 * rentalDays;

        // Calculate additional service costs
        double electronicTollTagCost = (tollTag) ? 3.95 * rentalDays : 0.0;
        double gpsCost = (gps) ? 2.95 * rentalDays : 0.0;
        double roadsideAssistanceCost = (roadsideAssistance) ? 3.95 * rentalDays : 0.0;

        // Calculate total cost without surcharge
        double totalCost = basicRentalCost + electronicTollTagCost + gpsCost + roadsideAssistanceCost;

        // Apply underage driver surcharge if applicable
        double rate = 1.30;
        if (age <= 25) {
            totalCost *= rate;
        }

        // Display rental details and total cost
        System.out.println("Rental Details:");
        System.out.println("Pick-up Date: " + pickupDate);
        System.out.println("Rental Days: " + rentalDays);
        System.out.printf("Basic Rental Cost: $%.2f" , basicRentalCost);
        System.out.println("");
        System.out.printf("Electronic Toll Tag Cost: $%.2f" , electronicTollTagCost);
        System.out.println("");
        System.out.printf("GPS Cost: $%.2f" , gpsCost);
        System.out.println("");
        System.out.printf("Roadside Assistance Cost: $%.2f" , roadsideAssistanceCost);
        System.out.println("");
        System.out.printf("Total Cost: $%.2f" , totalCost);

        scanner.close();
    }
}
