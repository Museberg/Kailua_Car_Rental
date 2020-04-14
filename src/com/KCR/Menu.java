package com.KCR;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu {

    public static void showMenu() throws SQLException {
        System.out.println("Welcome to Kailua Car Rental!");
        System.out.println("Please select an option from the menu%n");
        int option = -1;
        while(option != 0){
            printOptions();
            option = GetInput.getOptionFromUser(0, 10);
            executeOption(option);
        }
        System.out.printf("All data has been saved. Exiting program...");
    }

    public static void printOptions() throws SQLException{
        System.out.printf("%d - Cars%n", 1);
        System.out.printf("%d - Renters%n", 2);
        System.out.printf("%d - Contracts%n", 3);
        System.out.printf("%d - Save and exit%n", 0);
    }

    public static void executeOption(int option) throws SQLException {
        int id;
        switch (option) {
            case 1: // Show list of cars
                carMenu();
                break;
            case 2: // Show car with ID
                //rentersMenu();
                break;
            case 3: // Update car info
                //contractsMenu();
                break;
            case 0: // Save and exit
                break;
        }
    }

    public static void carMenu() throws SQLException {
        int option = -1;
        while(option != 0) {
            System.out.printf("%d - Show all cars%n", 1);
            System.out.printf("%d - Show car with ID%n", 2);
            System.out.printf("%d - Update car info%n", 3);
            System.out.printf("%d - Register new car%n", 4);
            System.out.printf("%d - Delete car%n", 5);
            System.out.printf("%d - Back to main menu%n", 0);

            option = GetInput.getOptionFromUser(0, 5);
            int id;
            switch (option){
                case 1: // Show list of cars
                    CarController.showAllCars();
                    break;
                case 2: // Show car with ID
                    CarController.showAllCars();
                    System.out.printf("Please select an ID from the list above%n");
                    id = GetInput.getIntFromUser("ID");
                    CarController.showCar(id);
                    break;
                case 3: // Update car info
                    CarController.showAllCars();
                    System.out.printf("Please select an ID from the list above%n");
                    id = GetInput.getIntFromUser("ID");
                    CarController.updateCar(id);
                    break;
                case 4: // Register new car
                    CarController.createCar();
                    break;
                case 5: // Delete car
                    CarController.showAllCars();
                    System.out.printf("Please select an ID from the list above%n");
                    id = GetInput.getIntFromUser("ID");
                    CarController.deleteCar(id);
                    break;
            }
        }
    }
}
