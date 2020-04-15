package com.KCR;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractController {
    public static void showAllContracts() throws SQLException {
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery("SELECT * FROM contracts");

        while(rs.next()){
            int id = rs.getInt("id");
            int renter_id = rs.getInt("renter_id");
            int car_id = rs.getInt("car_id");
            String renterQuery = String.format("SELECT * FROM contracts JOIN renters ON contracts.renter_id = renters.id WHERE renters.id = '%d'", renter_id);
            String carQuery = String.format("SELECT * FROM contracts LEFT JOIN cars ON contracts.car_id = cars.id WHERE cars.id = '%d'", car_id);
            ResultSet rsRenters = con.executeQuery(renterQuery);
            rsRenters.next();
            ResultSet rsCars = con.executeQuery(carQuery);
            rsCars.next();


            System.out.printf("ID: %d, ", rs.getInt("id"));
            System.out.printf("Renter: %s %s, ", rsRenters.getString("first_name"), rsRenters.getString("last_name"));
            System.out.printf("Car: %s %s, ", rsCars.getString("model"), rsCars.getString("registration_number"));
            System.out.printf("Timespan: %s - %s, ", DateHelper.dateToString(rs.getDate("start_date")), DateHelper.dateToString(rs.getDate("end_date")));

            System.out.printf("%n");
        }
        System.out.printf("%n");
    }

    public static void showContract(int id) throws SQLException {
        Connector con = Connector.getInstance();
        id = getValidId(id, con);
        ResultSet rs = con.executeQuery("SELECT * FROM contracts WHERE id = " + id);
        String renterQuery = String.format("SELECT * FROM contracts INNER JOIN renters ON contracts.renter_id = '%d'", id);
        String carQuery = String.format("SELECT * FROM contracts INNER JOIN cars ON contracts.car_id = '%d'", id);
        String brandQuery = String.format("SELECT * FROM contracts INNER JOIN brands ON cars.model = '%d'", id);
        ResultSet rsRenters = con.executeQuery(renterQuery);
        ResultSet rsCars = con.executeQuery(carQuery);
        rs.next();

        System.out.println("Contracts:");
        System.out.printf("%-15s %d%n", "ID:", rs.getInt("id"));
        System.out.printf("%-15s %d%n", "Car ID:", rsCars.getInt("car_id"));
        System.out.printf("%-15s %s%n", "Driver since:", rs.getDate("driver_since"));
        System.out.printf("%-15s %d%n", "Renter ID:", rsRenters.getInt("renter_id"));
        System.out.printf("%-15s %s%n", "Timespan", rs.getString("telephone"));
        System.out.printf("%-15s %d%n", "Start KM:", rs.getInt("start_km"));
        System.out.printf("%-15s %d%n", "Max KM:", rs.getInt("max_km"));
        System.out.printf("%-15s %s%n", "Driver since:", rs.getDate("driver_since"));
        System.out.printf("%n");
    }

    public static void createContract(){
        Connector con = Connector.getInstance();

    }

    private static int getValidId(int id, Connector con) throws SQLException {
        ResultSet rs = con.executeQuery("SELECT * FROM contracts WHERE id = " + id);
        while(rs.next() == false){
            System.out.printf("No renters found with the given ID. Please select an ID from the following list:%n");
            showAllContracts();
            id = GetInput.getIntFromUser("ID");
            rs = con.executeQuery("SELECT * FROM contracts WHERE id = " + id);
        }
        return id;
    }

}
