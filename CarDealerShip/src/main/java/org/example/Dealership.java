package org.example;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    public ArrayList<Vehicle> inventory = new ArrayList<>();

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (min <= vehicle.getPrice() && vehicle.getPrice() <= max) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for(Vehicle vehicle: inventory){
            if(vehicle.getMake().equalsIgnoreCase(make)||vehicle.getModel().equalsIgnoreCase(model)){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for(Vehicle vehicle: inventory){
            if (min <= vehicle.getYear() && vehicle.getYear()<= max){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for(Vehicle vehicle: inventory){
            if (vehicle.getColor().equalsIgnoreCase(color)){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for(Vehicle vehicle: inventory){
            if (min <= vehicle.getOdometer() && vehicle.getOdometer()<= max){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }
    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
        for(Vehicle vehicle: inventory){
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }
    public ArrayList<Vehicle> getAllVehicles(){
        return this.inventory;
    }

    public void addVehicle(Vehicle vehicle){
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        this.inventory.remove(vehicle);
    }

}





