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
            option = GetInput.getOptionFromUser(0, 1);
            executeOption(option);
        }
        System.out.printf("All data has been saved. Exiting program...");
    }

    public static void printOptions(){
        System.out.printf("%d - Show all cars%n", 1);
        System.out.printf("%d - Show car with ID%n", 2);
        System.out.printf("%d - Save and exit%n", 0);

        System.out.printf("%nSelect: ");
    }

    public static void executeOption(int option) throws SQLException {
        switch (option){
            case 1: // Show list of cars
                CarController.showAllCars();
                break;
            case 2: // Show car with ID
                break;


        }
    }


}
