package com.pluralsight;
import java.util.*;

public class UserInterface {

    private Dealership dealership;

    public void UserInterface() {

    };
    public void display() {
        init();
        Scanner scanner = new Scanner(System.in);
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
    };
    public void processGetByPriceRequest() {};
    public void processGetByMakeModelRequest() {};
    public void processGetByYearRequest() {};
    public void processGetByColorRequest() {};
    public void processGetByMileageRequest() {};
    public void processGetByVehicleTypeRequest() {};
    public void processGetAllVehiclesRequest() {};
    public void processAddVehicleRequest() {};
    public void processRemoveVehicleRequest() {};
    private void init() {
        DealershipFileManagement dealershipFileManagement = new DealershipFileManagement();
        this.dealership = dealershipFileManagement.getDealership();
    }

}
