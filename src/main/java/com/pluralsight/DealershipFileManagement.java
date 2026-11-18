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
                int vin = Integer.parseInt(tokens[0]);
                int year = Integer.parseInt(tokens[1]);
                String make = tokens[2];
                String model = tokens[3];
                String vehicleType = tokens[4];
                String color = tokens[5];
                int odometer = Integer.parseInt(tokens[6]);
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(tokens[7]));

                vehicle = new Vehicle(vin, year, make, model,vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }
    public void saveDealership() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv"))) {
            writer.write(getDealership().getName() + " | " + getDealership().getAddress() + " | " + getDealership().getAddress());
            for (Vehicle vehicle : getDealership().getAllVehicles()) {
                writer.write("\n" + vehicle.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
