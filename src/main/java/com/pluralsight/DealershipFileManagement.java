package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;

public class DealershipFileManagement {
    public Dealership getDealership() {
        Dealership dealership;
        Vehicle vehicle;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("inventory.csv"));
            String csvLine = bufferedReader.readLine();
            String[] tokens = csvLine.split("\\|");//ArrayList<String> tokens = new A
            String dealershipName = tokens[0];
            String dealershipAddress = tokens[1];
            String dealershipNumber = tokens[2];
            dealership = new Dealership(dealershipName, dealershipAddress, dealershipNumber);
            while ((csvLine = bufferedReader.readLine()) != null) {
                tokens = csvLine.split("\\|");
                int vin = Integer.parseInt(tokens[0].trim());
                int year = Integer.parseInt(tokens[1].trim());
                String make = tokens[2];
                String model = tokens[3];
                String vehicleType = tokens[4];
                String color = tokens[5];
                int odometer = Integer.parseInt(tokens[6].trim());
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(tokens[7]));

                vehicle = new Vehicle(vin, year, make, model,vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);



            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }
    public void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv"))) {
            writer.write(dealership.getName() + " | " + dealership.getAddress() + " | " + dealership.getPhone());
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write("\n" + vehicle.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
