package org.example;


import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public UserInterface(Dealership dealership){
        this.dealership = DealershipFileManager.getDealership();
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
                        DealershipFileManager.saveDealership(dealership);
                        break;
                    case 9:
                        processRemoveVehicle();
                        DealershipFileManager.saveDealership(dealership);
                        break;
                    case 10:
                        processBuyVehicle();
                        DealershipFileManager.saveDealership(dealership);
                        break;
                    case 11:
                        processLeasedVehicle();
                        DealershipFileManager.saveDealership(dealership);
                        break;
                    case 0:
                        DealershipFileManager.saveDealership(dealership);
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
    public void processBuyVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are purchasing a vehicle");
        boolean isFinanced = false;
        boolean vinFound = false;
        String choice = null;

        System.out.println("Please enter the VIN of the car you would like to buy");
        if (scanner.hasNextInt()) {
            int vinToSearch = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Would you like to finance? (Yes/No)");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                isFinanced = true;
            } else if (choice.equalsIgnoreCase("no")) {
                isFinanced = false;
            } else {
                System.out.println("Invalid answer, please enter yes or no.");
                return;
            }
            System.out.println("Enter the following information below: \n");
            System.out.println("Please enter the date.");
            String date = scanner.nextLine();
            System.out.println("Please enter your name.");
            String customerName = scanner.nextLine();
            System.out.println("Please enter your email.");
            String customerEmail = scanner.nextLine();
            List<Vehicle> vehicles = dealership.getAllVehicles();
            for (Vehicle vehicle : vehicles) {

                if (vinToSearch == vehicle.getVin()) {

                    vinFound = true;
                    SalesContract salesContract = new SalesContract((LocalDate.now()), customerName, customerEmail, vehicle, false);
                    salesContract.getTotalPrice();
                    salesContract.getMonthlyPayment();
                    System.out.printf("Your Total Price will be: %.2f %n", salesContract.getTotalPrice());
                    System.out.printf("Your monthly payment will be %.2f %n", salesContract.getMonthlyPayment());
                    System.out.println("Would you like to buy it?");
                    if (scanner.hasNextLine()) {

                        String buyChoice = scanner.nextLine();
                        if (buyChoice.equalsIgnoreCase("yes")) {

                            dealership.removeVehicle(vehicle);
                            System.out.println("Removed vehicle with VIN " + vinToSearch);

                            ContractFileManager.writeCustomerInfoToFile("SALE", date, customerName, customerEmail, vehicle, salesContract.salesTaxAmount(), salesContract.recordingFee(),
                                    salesContract.processingFee(), salesContract.getTotalPrice(), choice, salesContract.getMonthlyPayment());
                            System.out.println("Car successfully purchased. CONGRATS!!!");
                        } else {
                            System.out.println("Contract Rejected. Returning back to the main screen.");
                        }
                    }
                    break;
                }
            }
            if (!vinFound) {
                System.out.println("Vin not found. Returning to the main screen.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid VIN.");
        }
    }
    public void processLeasedVehicle(){
        System.out.println("You are leasing a vehicle");
        System.out.println("Enter the VIN of the vehicle");
        Scanner scanner = new Scanner(System.in);
        boolean vinWasFound = false;
        if (scanner.hasNextLine()){
            int vinToSearch = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please enter the following information.");
            System.out.println("Please enter the date.");
            String date = scanner.nextLine();
            System.out.println("Please enter your name");
            String customerName = scanner.nextLine();
            System.out.println("Please enter your email");
            String customerEmail = scanner.nextLine();
            List<Vehicle> vehicles = dealership.getAllVehicles();
            //Iterator is reads over the collection of my list and allows me to modify it without running into the problem before.
            Iterator<Vehicle> iterator = vehicles.iterator();
            while (iterator.hasNext()) {
                Vehicle vehicle = iterator.next();
                if (vinToSearch == vehicle.getVin()) {
                    vinWasFound = true;
                    LeaseContract leaseContract = new LeaseContract(date, customerName, customerEmail, vehicle);
                    leaseContract.getTotalPrice();
                    leaseContract.getMonthlyPayment();

                    System.out.printf("Your total price will be: %.2f \n", leaseContract.getTotalPrice());
                    System.out.printf("Your monthly payment will be: %.2f \n", leaseContract.getMonthlyPayment());

                    System.out.println("Would you like to lease the vehicle? (Yes / No)");
                    if (scanner.hasNextLine()) {
                        String userLeaseChoice = scanner.nextLine();
                        if (userLeaseChoice.equalsIgnoreCase("yes")) {
                            ContractFileManager.writeCustomerLease("LEASE", date, customerName, customerEmail, vehicle, leaseContract.getTotalPrice(),
                                    leaseContract.expectedEndingValue(), leaseContract.leaseFee(), leaseContract.getMonthlyPayment());
                            System.out.println("Car successfully leased. CONGRATS!!!");
                            iterator.remove();
                        } else {
                            System.out.println("Lease Declined. Return to the main page");
                        }
                    }
                }
            }

            if (!vinWasFound) {
                System.out.println("Vin not found. Returning to the main screen.");
            }
        }
        else {
            System.out.println("Invalid input. Please enter a valid VIN.");
        }
    }
}





