package com.KCR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class CarController {

    /*public static ResultSet getAllCars() throws SQLException{
        Connector con = Connector.getInstance();
        return con.executeQuery("SELECT * FROM cars");
    }

    public static ResultSet getCar(int id) throws SQLException{
        Connector con = Connector.getInstance();
        return con.executeQuery("SELECT * FROM cars WHERE id = " + id);
    }*/

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

    public static void createCar() throws SQLException{
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery("SELECT * FROM cars WHERE id");
        Scanner input = new Scanner(System.in);

        System.out.printf("Which type of car would you like to register?%n");
        System.out.printf("%d - Luxury%n", 1);
        System.out.printf("%d - Sport%n", 2);
        System.out.printf("%d - Family%n", 3);

        int option = GetInput.getOptionFromUser(1, 3);

        String query;

        insertCar(option);
        // Luxury
            // >3000ccm, auto-gear, ac, cc, leather
        // Sport
            // manual, >200 hp
        // Family
            // manual, >=7, ac, cc yes/no
    }

    public static void insertCar(int option) {
        Scanner input = new Scanner(System.in);

        String regNumber = GetInput.getStringOfLength(10, "Registration number");

        LocalDate firstRegistration = DateHelper.getValidDateFromUser("First registration (yyyy-MM-dd)");

        System.out.printf("%nFuel type");
        System.out.printf("%n%d - Gasoline", 1);
        System.out.printf("%n%d - Diesel", 2);
        System.out.printf("%n%d - Electric", 3);

        int fuelOption = GetInput.getOptionFromUser(1, 3);
        String fuelType;
        switch(fuelOption) {
            case 1:
                fuelType = "Gasoline";
                break;
            case 2:
                fuelType = "Diesel";
                break;
            case 3:
                fuelType = "Electric";
                break;
            default:
                break;
        }

        int odometer = GetInput.getIntFromUser("Odometer");

        String brand = GetInput.getStringOfLength(20, "Brand");

        String model = GetInput.getStringOfLength(20, "Model");

        int ccm = GetInput.getIntFromUser("Ccm");
        if(option == 1) {
            while(ccm < 3000) {
                System.out.printf("All luxury models must have a ccm of at least 3000!");
                ccm = GetInput.getIntFromUser("Ccm");
            }
        }

        int hp = GetInput.getIntFromUser("Horse power");
        if(option == 2) {
            while(hp < 200) {
                System.out.printf("All sports models must have at least 200 horse power!");
                hp = GetInput.getIntFromUser("Horse power");
            }
        }

        String seatType;
        if(option == 2 || option == 3) {
            seatType = GetInput.getStringOfLength(20, "Seat type");
        }

        int seatNumber = GetInput.getIntFromUser("Seat number");
        if(option == 3) {
            while(seatNumber < 7) {
                System.out.println("All family models must have at least 7 seats!");
                seatNumber = GetInput.getIntFromUser("Seat number");
            }
        }

        boolean cruiseControl;
        if(option == 2 || option == 3) {
            System.out.printf("%nCruise control (1 for yes / 2 for no)");
            cruiseControl = 1 == GetInput.getOptionFromUser(1, 2);
        }

        String gearType;
        boolean airCon;

        switch(option){
            case 1: // Luxury
                gearType = "automatic";
                airCon = true;
                seatType = "leather";
                cruiseControl = true;
            case 2: // Sport
                gearType = "manual";
                System.out.printf("%nAir conditioning (1 for yes / 2 for no)");
                airCon = 1 == GetInput.getOptionFromUser(1, 2);
            case 3: // Family
                gearType = "manual";
                airCon = true;
        }
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
                int newOdo = GetInput.getIntFromUser("Odometer");
                query = "UPDATE cars " +
                        "SET odometer = " + newOdo + " " +
                        "WHERE id = " + id;
                con.executeUpdate(query);
                break;
        }

    }
}
