package com.KCR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CarController {

    public static void showAllCars() throws SQLException {
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery("SELECT * FROM cars");
        while(rs.next()){
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
    }

    public void showCar(int id){

    }
}
