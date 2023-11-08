package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class DealershipFileManager {
    //This is good practice, the file name is a constant
    //Would be nice to implement a save though especially when we do the contract
    private static final String VEHICLES_FILE_PATH = "src/main/resources/Vehicles.csv";

    public static Dealership getDealership() {
        List<Vehicle> vehicleList = new ArrayList<>();
        Dealership dealership = new Dealership("Big Buck Dealership","555 highland park","214-555-2023");

        try {
            try (FileInputStream fileInputStream = new FileInputStream(VEHICLES_FILE_PATH);
                 Scanner scanner = new Scanner(fileInputStream)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] vehicle = line.split(",");
                    if (vehicle.length == 8) {
                        int vin = Integer.parseInt(vehicle[0]);
                        int year = Integer.parseInt(vehicle[1]);
                        String make = vehicle[2];
                        String model = vehicle[3];
                        String vehicleType = vehicle[4];
                        String color = vehicle[5];
                        int odometer = Integer.parseInt(vehicle[6]);
                        double price = Double.parseDouble(vehicle[7]);

                        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                        vehicleList.add(newVehicle);

                        dealership.addVehicle(newVehicle);
                    } else {
                        System.out.println("Invalid data in the file: " + VEHICLES_FILE_PATH);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("File not found: " + VEHICLES_FILE_PATH);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid data format in the file: " + VEHICLES_FILE_PATH);
        }

        return dealership;
    }
}

