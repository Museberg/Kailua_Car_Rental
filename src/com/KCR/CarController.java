package com.KCR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CarController {

    public static void showAllCars() throws SQLException {
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery("SELECT * FROM cars");
        while(rs.next()){
            System.out.println("Car:");
            System.out.printf("%-15s %d%n", "ID:", rs.getInt("id"));
            System.out.printf("%-15s %s%n", "Reg. Number:", rs.getString("registration_number"));
            System.out.printf("%-15s %s%n", "Model:", rs.getString("model"));
            System.out.printf("%-15s %s%n", "Car type:", rs.getString("car_type"));
            System.out.printf("%n");
        }
    }


    public static void showCar(int id) throws SQLException {
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery("SELECT * FROM cars WHERE id = " + id);
        if(rs.next() == false){
            System.out.printf("No cars found with the given ID. Please select an ID from the following list:%n");
            showAllCars();
            Menu.executeOption(2); // Returning user to menu
            return;
        }

        System.out.println("Car:");
        System.out.printf("%-15s %d%n", "ID:", rs.getInt("id"));
        System.out.printf("%-15s %s%n", "Reg. Number:", rs.getString("registration_number"));
        System.out.printf("%-15s %s%n", "First reg.:", rs.getDate("first_registration"));
        System.out.printf("%-15s %s%n", "Fuel type:", rs.getString("fuel_type"));
        System.out.printf("%-15s %d%n", "Odometer:", rs.getInt("odometer"));
        System.out.printf("%-15s %s%n", "Model:", rs.getString("model"));
        System.out.printf("%-15s %s%n", "Car type:", rs.getString("car_type"));
        System.out.printf("%-15s %s%n", "Gear type:", rs.getString("gear_type"));
        System.out.printf("%-15s %s%n", "Air con.:", rs.getInt("air_conditioning") == 1 ? "Yes" : "No");
        System.out.printf("%-15s %d%n", "CCM:", rs.getInt("ccm"));
        System.out.printf("%-15s %d%n", "HP:", rs.getInt("hp"));
        System.out.printf("%-15s %s%n", "Seat type:", rs.getString("seat_type"));
        System.out.printf("%-15s %d%n", "Seat number:", rs.getInt("seat_number"));
        System.out.printf("%-15s %s%n", "Cruise Control:", rs.getInt("cruise_control") == 1 ? "Yes" : "No");
        System.out.printf("%n");
    }

    public static void updateCar(int id) throws SQLException {
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery("SELECT * FROM cars WHERE id = " + id);
        if(rs.next() == false){
            System.out.printf("No cars found with the given ID. Please select an ID from the following list:%n");
            showAllCars();
            Menu.executeOption(2); // Returning user to menu
            return;
        }

        System.out.printf("What do you wan to update?%n");
        System.out.printf("%d - Registration number%n", 1);
        System.out.printf("%d - Odometer%n", 2);

        int option = GetInput.getOptionFromUser(1, 2);

        String query;
        switch(option){
            case 1: // Update registration number
                Scanner scan = new Scanner(System.in);
                System.out.printf("What do you want to update the reg number to?%n");
                String newRegNumber = scan.next();
                query = "UPDATE cars " +
                        "SET registration_number = '" + newRegNumber +
                        "' WHERE id = " + id;
                con.executeUpdate(query);
                break;
            case 2: // Update odometer
                System.out.printf("What do you want to update the odometer to?%n");
                int newOdo = GetInput.getIntFromUser();
                query = "UPDATE cars " +
                        "SET odometer = " + newOdo + " " +
                        "WHERE id = " + id;
                con.executeUpdate(query);
                break;
        }

    }

}
