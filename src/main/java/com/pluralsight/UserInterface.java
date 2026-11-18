package com.pluralsight;
import java.math.BigDecimal;
import java.util.*;

public class UserInterface {

    private Dealership dealership;
    Scanner scanner = new Scanner(System.in);
    DealershipFileManagement dealershipFileManagement = new DealershipFileManagement();

    public void UserInterface() {}
    public void display() {
        init();

        boolean inMenu = true;
        while (inMenu) {
            System.out.println("Main Menu \n Please choose one of the following options");
            System.out.println("""
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make/model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type (car, truck, SUV, van)
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    99 - Quit
                    \s""");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeModelRequest();
                case "3" -> processGetByYearRequest();
                case "4" -> processGetByColorRequest();
                case "5" -> processGetByMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processGetAllVehiclesRequest();
                case "8" -> processAddVehicleRequest();
                case "9" -> processRemoveVehicleRequest();
                case "99" -> inMenu = false;
                default -> System.out.println("Please reenter your selection.");

            }

        }
    }
    public void processGetByPriceRequest() {
        System.out.println("Enter your minimum price");
        BigDecimal min = BigDecimal.valueOf(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Enter your maximum price");
        BigDecimal max = BigDecimal.valueOf(scanner.nextInt());
        scanner.nextLine();
        displayVehicles(dealership.getVehiclesByPrice(min,max));
    }
    public void processGetByMakeModelRequest() {
        System.out.println("Enter the make");
        String make = scanner.nextLine().trim();
        System.out.println("Enter the model");
        String model = scanner.nextLine().trim();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }
    public void processGetByYearRequest() {
        System.out.println("Enter the newest year");
        int min = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the oldest year");
        int max = scanner.nextInt();
        scanner.nextLine();
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }
    public void processGetByColorRequest() {
        System.out.println("Enter the color");
        String color = scanner.nextLine().trim();
        displayVehicles(dealership.getVehiclesByColor(color));
    }
    public void processGetByMileageRequest() {
        System.out.println("Enter the minimum mileage");
        int min = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the maximum mileage");
        int max = scanner.nextInt();
        scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }
    public void processGetByVehicleTypeRequest() {
        System.out.println("Enter the vehicle type (car, truck, SUV, van");
        String type = scanner.nextLine().trim();
        displayVehicles(dealership.getVehiclesByType(type));
    }
    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }
    public void processAddVehicleRequest() {
        System.out.println("Enter the VIN");
        int vin = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the year");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the make");
        String make = scanner.nextLine();
        System.out.println("Enter the model");
        String model = scanner.nextLine();
        System.out.println("Enter the vehicle body style (SUV, car, van, truck, etc.)");
        String vehicleType = scanner.nextLine();
        System.out.println("Enter the color");
        String color = scanner.nextLine();
        System.out.println("Enter the mileage from the odometer");
        int odometer = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the price");
        BigDecimal price = new BigDecimal(scanner.nextInt());

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);

        System.out.println("You have added a vehicle");

        dealershipFileManagement.saveDealership(dealership);

    }
    public void processRemoveVehicleRequest() {
        System.out.println("Enter the VIN");
        int vin = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                found = true;
            }
        }
        if (found){
            System.out.println("The vehicle was removed from inventory");
        }else {
            System.out.println("No vehicles found");
        }

        dealershipFileManagement.saveDealership(dealership);

    }
    private void init() {
        this.dealership = dealershipFileManagement.getDealership();
    }
    private void displayVehicles(List list) {
        for (Object o : list) {
            System.out.println(o.toString());
        }

    }

}
