package org.example;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Dealership dealership;
    public Dealership getDealership() {
        return dealership;
    }

    //So you don't need to instantiate the dealershipfilemanager at all, the methods are static
    //Also, nothing stops me from putting a dealership with no vehicles. What you could do
    //is load the vehicles in the constructor like this:
    public UserInterface(){
        //You decide how this gets loaded so there are no possible mistakes
        this.dealership = DealershipFileManager.getDealership();
    }
    public UserInterface(Dealership dealership) {
        this.dealership = dealership;
        DealershipFileManager dfm = new DealershipFileManager();

    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean MakingSelection = true;

        while (MakingSelection) {
            try {
                System.out.println("Welcome to the Dealership Application Menu.");
                System.out.println("1 - Find vehicles within a price range.");
                System.out.println("2 - Find vehicles by make/model.");
                System.out.println("3 - Find vehicles by year range.");
                System.out.println("4 - Find vehicles by color.");
                System.out.println("5 - Find vehicles by mileage range.");
                System.out.println("6 - Find vehicles by type (car, truck, SUV, van).");
                System.out.println("7 - List ALL vehicles.");
                System.out.println("8 - Add a vehicle.");
                System.out.println("9 - Remove a vehicle.");
                System.out.println("0 - Quit.");
                System.out.println("Please enter a number: ");
                int UserInput = scanner.nextInt();

                switch (UserInput) {
                    case 1:
                        processGetALLPriceByPrice();
                        break;
                    case 2:
                        processGetMakeModel();
                        break;
                    case 3:
                        processGetByYear();
                        break;
                    case 4:
                        processGetByColor();
                        break;
                    case 5:
                        processGetByMileage();
                        break;
                    case 6:
                        processGetByVehicleType();
                        break;
                    case 7:
                        processGetAllVehicles();
                        break;
                    case 8:
                        processAddVehicle();
                        break;
                    case 9:
                        processRemoveVehicle();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Not an option, please try again.");
                }
            } catch (InputMismatchException exception) {
                System.out.println("Please select a number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }


    private void processGetALLPriceByPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();
        List<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles of the specified price range found.");
        } else {
            System.out.println("Vehicles of the specified price range:");
            for (Vehicle vehicle : filteredVehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private void processGetMakeModel() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();
        List<Vehicle> filteredVehicles = dealership.getVehiclesByMakeModel(make, model);
        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles of the specified make and model found.");
        } else {
            System.out.println("Vehicles of the specified make and model:");
            for (Vehicle vehicle : filteredVehicles) {
                System.out.println(vehicle);
            }
        }


    }

    private void processGetByYear() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();
        List<Vehicle> filteredVehicles = dealership.getVehiclesByYear(minYear, maxYear);
        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles of the specified year range found.");
        } else {
            System.out.println("Vehicles of the specified year range:");
            for (Vehicle vehicle : filteredVehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private void processGetByColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter color: ");
        String color = scanner.next();
        List<Vehicle> filteredVehicles = dealership.getVehiclesByColor(color);
        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles of the specified color found.");
        } else {
            System.out.println("Vehicles of the specified color:");
            for (Vehicle vehicle : filteredVehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private void processGetByMileage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum mileage: ");
        int minMileage = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int maxMileage = scanner.nextInt();
        List<Vehicle> filteredVehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles of the specified range found.");
        } else {
            System.out.println("Vehicles of the specified mileage range:");
            for (Vehicle vehicle : filteredVehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private void processGetByVehicleType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String vehicleType = scanner.next();
        List<Vehicle> filteredVehicles = dealership.getVehiclesByType(vehicleType);
        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles of the specified type found.");
        } else {
            System.out.println("Vehicles of the specified type:");
            for (Vehicle vehicle : filteredVehicles) {
                System.out.println(vehicle);
            }
        }
    }

    public void processGetAllVehicles(){
        boolean isGettingByVehicleType = true;

        while (isGettingByVehicleType) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the vehicle type you are looking for:");
                String vehicleType = scanner.nextLine();

                List<Vehicle> vehicleList = dealership.getVehiclesByType(vehicleType);
                isGettingByVehicleType = false;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input.");
            }
        }
    }



    private void processAddVehicle(){
        System.out.println("Please enter the following information.");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Enter the vehicle's year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the vehicle's make: ");
        String make = scanner.nextLine();
        System.out.print("Enter the vehicle's model: ");
        String model = scanner.nextLine();
        System.out.print("Enter the vehicle type (car, truck, SUV, van): ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter the vehicle's color: ");
        String color = scanner.nextLine();
        System.out.print("Enter the vehicle's odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Enter the vehicle's price: ");
        double price = scanner.nextDouble();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle);
        System.out.println("New vehicle added successfully.\n");
    }

    private void processRemoveVehicle(){
        System.out.println("Enter the vin of the vehicle you want to remove");
        Scanner scanner = new Scanner(System.in);
        int vinToRemove = scanner.nextInt();
        List<Vehicle> inventory = dealership.getAllVehicles();
        boolean removed = false;

        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin() == vinToRemove) {
                dealership.removeVehicle(vehicle);
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Vehicle with VIN " + vinToRemove + " has been removed from the inventory.\n");
        } else {
            System.out.println("No vehicle found with VIN " + vinToRemove + " in the inventory.\n");
        }
    }

}





