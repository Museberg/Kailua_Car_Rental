package com.KCR;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractController {
    public static void showAllContracts() throws SQLException {
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery("SELECT * FROM contracts");

        while(rs.next()){
            int id = rs.getInt("id");
            String renterQuery = String.format("SELECT * FROM contracts LEFT JOIN renters ON contracts.renter_id = '%d'", id);
            String carQuery = String.format("SELECT * FROM contracts LEFT JOIN cars ON contracts.renter_id = '%d'", id);
            ResultSet rsRenters = con.executeQuery(renterQuery);
            ResultSet rsCars = con.executeQuery(carQuery);

            System.out.printf("ID: %d, ", rs.getInt("id"));

            System.out.printf("%n");
        }
        System.out.printf("%n");
    }

    public static void showContract(int id) throws SQLException {
        Connector con = Connector.getInstance();
        id = getValidId(id, con);
        ResultSet rs = con.executeQuery("SELECT * FROM contracts WHERE id = " + id);
        rs.next();

        System.out.println("Car:");
        System.out.printf("%-15s %d%n", "ID:", rs.getInt("id"));
        System.out.printf("%-15s %s%n", "First name:", rs.getString("first_name"));
        System.out.printf("%-15s %s%n", "Last name.:", rs.getString("last_name"));
        System.out.printf("%-15s %s%n", "Tel:", rs.getString("telephone"));
        System.out.printf("%-15s %s%n", "E-mail:", rs.getString("email"));
        System.out.printf("%-15s %s%n", "Drivers L.:", rs.getString("driver_license"));
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
            showAllRenters();
            id = GetInput.getIntFromUser("ID");
            rs = con.executeQuery("SELECT * FROM contracts WHERE id = " + id);
        }
        return id;
    }

}
