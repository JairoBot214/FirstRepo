package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public static void writeCustomerInfoToFile(String sale, String date, String customerName, String customerEmail,
                                               Vehicle vehicle, double salesTax, double recordingFee, double processingFee,
                                               double total, String isFinanced, double monthlyPayment) {
        try (FileWriter writer = new FileWriter("src/main/resources/Sales_Info.csv", true)) {
            String customerInfo = String.format("SaleType: %s|Date: %s|Name: %s|Email: %s|VIN: %d|Year: Xd|Make: %s|Model: %s|Type: %s|Color: %s|SalesTax: %.2f" +
                            "|RecordingFee: %.2f|ProcessingFee: %.2f|Total: %.2f|Financed: %s|MonthlyPayment: %.2f",
                    sale, date, customerName, vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(),
                    vehicle.getColor(), salesTax, recordingFee, processingFee, total, isFinanced, monthlyPayment);

            writer.write(customerInfo + System.lineSeparator());
            System.out.println("Written to the file successfully!");

        } catch (IOException e) {
            System.out.println("Error inputting it into the file.");
        }
    }

    public static void writeCustomerLease(String saleType, String date, String customerName, String customerEmail, Vehicle vehicle,
                                          double totalPrice, double expectedEndingValue, double leaseFee, double monthlyPayment ){
        try(FileWriter writer = new FileWriter("src/main/resources/Sales_Info.csv", true)){
            String customerInfo = String.format("SaleType: %s|Date: %s|Name: %s|Email: %s|VIN: %d|Year: %d|Make: %s|Model: %s|Type: %s|Color: %s|Total: %.2f|EndingValue: %.2f|LeaseFee: %.2f|MonthlyPayment: %.2f",
                    saleType, date, customerName, customerEmail, vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                    vehicle.getVehicleType(), vehicle.getColor(), totalPrice, expectedEndingValue, leaseFee, monthlyPayment);

            writer.write(customerInfo + System.lineSeparator());
            System.out.println("Transaction written successfully to file.");
        } catch(IOException ex){
            ex.printStackTrace();
            System.out.println("Error writing to the file.");
        }
    }
}
