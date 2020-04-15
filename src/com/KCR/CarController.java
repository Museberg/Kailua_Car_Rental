package com.KCR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
            System.out.printf("ID: %d, ", rs.getInt("id"));
            System.out.printf("Reg. Num: %s, ", rs.getString("registration_number"));
            System.out.printf("Model: %s, ", rs.getString("model"));
            System.out.printf("Type: %s", rs.getString("car_type"));
            System.out.printf("%n");
        }
        System.out.printf("%n");
    }

    public static void showCar(int id) throws SQLException {
        Connector con = Connector.getInstance();
        id = getValidId(id, con);
        ResultSet rs = con.executeQuery("SELECT * FROM cars WHERE id = " + id);
        rs.next();

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

        System.out.printf("Which type of car would you like to register?%n");
        System.out.printf("%d - Luxury%n", 1);
        System.out.printf("%d - Sport%n", 2);
        System.out.printf("%d - Family%n", 3);

        int option = GetInput.getOptionFromUser(1, 3);
        insertCar(option);
    }

    public static void insertCar(int option) throws SQLException {
        Connector con = Connector.getInstance();
        String regNumber = GetInput.getStringOfLength(10, "Registration number");
        String checkRegNumber = String.format("SELECT * FROM cars WHERE registration_number = '%s'", regNumber);
        ResultSet rs = con.executeQuery(checkRegNumber);
        while(rs.next()) {
            System.out.printf("A car with this registration number has already been registered. Please try again!");
            regNumber = GetInput.getStringOfLength(10, "Registration number");
            rs = con.executeQuery(checkRegNumber);
        }

        LocalDate firstRegistration = DateHelper.getValidDateFromUser("First registration (yyyy-MM-dd)");

        System.out.printf("%nFuel type");
        System.out.printf("%n%d - Gasoline", 1);
        System.out.printf("%n%d - Diesel", 2);
        System.out.printf("%n%d - Electric", 3);

        int fuelOption = GetInput.getOptionFromUser(1, 3);
        String fuelType = "";
        switch(fuelOption) {
            case 1:
                fuelType = "gasoline";
                break;
            case 2:
                fuelType = "diesel";
                break;
            case 3:
                fuelType = "electric";
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

        String seatType = "";
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

        boolean cruiseControl = false;
        if(option == 2 || option == 3) {
            System.out.printf("%nCruise control (1 for yes / 2 for no)");
            cruiseControl = 1 == GetInput.getOptionFromUser(1, 2);
        }

        String gearType = "";
        boolean airCon = false;
        String ct = "";

        switch(option){
            case 1: // Luxury
                gearType = "automatic";
                airCon = true;
                seatType = "leather";
                cruiseControl = true;
                ct = "luxury";
                break;
            case 2: // Sport
                gearType = "manual";
                System.out.printf("%nAir conditioning (1 for yes / 2 for no)");
                airCon = 1 == GetInput.getOptionFromUser(1, 2);
                ct = "sport";
                break;
            case 3: // Family
                gearType = "manual";
                airCon = true;
                ct = "family";
                break;
        }

        String checkBrand = String.format("SELECT * FROM brands WHERE brand_name = '%s'", brand);
        String queryBrand = String.format("INSERT INTO brands VALUES (0, '%s')", brand);
        // Inserting brand if not present in database
        int brandID = con.insertIfNotExistsInt(checkBrand, queryBrand);

        String checkModel = String.format("SELECT * FROM models WHERE model = '%s'", model);
        String queryModel = String.format("INSERT INTO models VALUES ('%s', '%d')", model, brandID);
        // Inserting model if not present in database
        rs = con.executeQuery(checkModel);
        if(!rs.next()) {
            con.executeUpdate(queryModel);
        }

        String queryCar = String.format("INSERT INTO cars VALUES (0, '%s', '%s', '%s', '%d', '%s', '%s', '%s', %b, '%d', '%d', '%s', '%d', %b)",
                regNumber, firstRegistration, fuelType, odometer, model, ct, gearType, airCon, ccm, hp, seatType, seatNumber, cruiseControl);
        con.executeUpdate(queryCar);
    }

    public static void updateCar(int id) throws SQLException {
        Connector con = Connector.getInstance();
        id = getValidId(id, con);
        ResultSet rs = con.executeQuery("SELECT * FROM cars WHERE id = " + id);
        rs.next();

        System.out.printf("What do you wan to update?%n");
        System.out.printf("%d - Registration number%n", 1);
        System.out.printf("%d - Odometer%n", 2);

        int option = GetInput.getOptionFromUser(1, 2);

        String query;
        switch(option){
            case 1: // Update registration number
                System.out.printf("What do you want to update the reg number to?%n");
                String newRegNumber = GetInput.getStringOfLength(10, "Registration number");
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

    public static void deleteCar(int id) throws SQLException {
        Connector con = Connector.getInstance();
        id = getValidId(id, con);
        ResultSet rs = con.executeQuery("SELECT * FROM cars WHERE id = " + id);
        rs.next();

        String deleteQuery = String.format("DELETE FROM cars WHERE id = %d", id);
        con.executeUpdate(deleteQuery);
    }

    private static int getValidId(int id, Connector con) throws SQLException {
        ResultSet rs = con.executeQuery("SELECT * FROM cars WHERE id = " + id);
        while(rs.next() == false){
            System.out.printf("No cars found with the given ID. Please select an ID from the following list:%n");
            showAllCars();
            id = GetInput.getIntFromUser("ID");
            rs = con.executeQuery("SELECT * FROM cars WHERE id = " + id);
        }
        return id;
    }
}

