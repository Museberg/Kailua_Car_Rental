package com.KCR;

import java.sql.SQLException;
import java.text.ParseException;

public class Menu {

    public static void showMenu() throws SQLException, ParseException {
        System.out.println("Welcome to Kailua Car Rental!");
        System.out.println("Please select an option from the menu");
        int option = -1;
        while(option != 0){
            printOptions();
            option = GetInput.getOptionFromUser(0, 3);
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

    public static void executeOption(int option) throws SQLException, ParseException {
        int id;
        switch (option) {
            case 1: // Show car menu
                carMenu();
                break;
            case 2: // Show renter menu
                renterMenu();
                break;
            case 3: // Show contract menu
                contractsMenu();
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


    public static void renterMenu() throws SQLException {
        int option = -1;

        while(option != 0) {
            System.out.printf("%d - Show all renters%n", 1);
            System.out.printf("%d - Show renter with ID%n", 2);
            System.out.printf("%d - Update renter info%n", 3);
            System.out.printf("%d - Register new renter%n", 4);
            System.out.printf("%d - Delete renter%n", 5);
            System.out.printf("%d - Back to main menu%n", 0);

            option = GetInput.getOptionFromUser(0, 5);
            int id;
            switch (option) {
                case 1: // Show list of renters
                    RenterController.showAllRenters();
                    break;
                case 2: // Show renter with ID
                    RenterController.showAllRenters();
                    System.out.printf("Please select an ID from the list above%n");
                    id = GetInput.getIntFromUser("ID");
                    RenterController.showRenter(id);
                    break;
                case 3: // Update renter info
                    System.out.println("Not implemented yet.");
                    break;
                case 4: // Register new renter
                    RenterController.createRenter();
                    break;
                case 5: // Delete renter
                    System.out.println("Not implemented yeet.");
                    break;

            }

        }

    }

    public static void contractsMenu() throws SQLException, ParseException {
        int option = -1;
        while(option != 0) {
            System.out.printf("%d - Show all contracts%n", 1);
            System.out.printf("%d - Show contract with ID%n", 2);
            System.out.printf("%d - Update contract info%n", 3);
            System.out.printf("%d - Register new contract%n", 4);
            System.out.printf("%d - Delete contract%n", 5);
            System.out.printf("%d - Back to main menu%n", 0);

            option = GetInput.getOptionFromUser(0, 5);
            int id;
            switch (option) {
                case 1: // Show list of contracts
                    ContractController.showAllContracts();
                    break;
                case 2: // Show contract with ID
                    ContractController.showAllContracts();
                    System.out.printf("Please select an ID from the list above%n");
                    id = GetInput.getIntFromUser("ID");
                    ContractController.showContract(id);
                    break;
                case 3: // Update contract info
                    System.out.println("Not implemented yet.");
                    break;
                case 4: // Register new contract
                    ContractController.createContract();
                    break;
                case 5: // Delete contract
                    System.out.println("Not implemented yet.");
                    break;
            }
        }
    }
}
