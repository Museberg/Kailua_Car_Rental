package com.KCR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RenterController {

    public static void showAllRenters() throws SQLException {
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery("SELECT * FROM renters");
        while(rs.next()){
            System.out.printf("ID: %d, ", rs.getInt("id"));
            System.out.printf("F. Name: %s, ", rs.getString("first_name"));
            System.out.printf("L. Name: %s, ", rs.getString("last_name"));
            System.out.printf("Tel: %s", rs.getString("telephone"));
            System.out.printf("%n");
        }
        System.out.printf("%n");
    }

    public static void showRenter(int id) throws SQLException {
        Connector con = Connector.getInstance();
        id = getValidId(id, con);
        ResultSet rs = con.executeQuery("SELECT * FROM renters WHERE id = " + id);
        rs.next();

        // Getting address ID and joining with table
        int addressID = rs.getInt("address_id");
        String addressQuery = String.format("SELECT * FROM renters LEFT JOIN" +
                " addresses ON renters.address_id = addresses.id WHERE addresses.id = %d", addressID);
        ResultSet rsAddress = con.executeQuery(addressQuery);
        rsAddress.next();

        // Joining with ZIP code table
        String zipCode = rsAddress.getString("zip_code");
        String zipQuery = String.format("SELECT * FROM addresses LEFT JOIN" +
                " zip_codes ON addresses.zip_code = zip_codes.zip WHERE zip_codes.zip = '%s'", zipCode);
        ResultSet rsZipCode = con.executeQuery(zipQuery);
        rsZipCode.next();

        System.out.println("Car:");
        System.out.printf("%-15s %d%n", "ID:", rs.getInt("id"));
        System.out.printf("%-15s %s%n", "First name:", rs.getString("first_name"));
        System.out.printf("%-15s %s%n", "Last name.:", rs.getString("last_name"));
        System.out.printf("%-15s %s%n", "Tel:", rs.getString("telephone"));
        System.out.printf("%-15s %s%n", "E-mail:", rs.getString("email"));
        System.out.printf("%-15s %s%n", "Drivers L.:", rs.getString("driver_license"));
        System.out.printf("%-15s %s%n", "Driver since:", rs.getDate("driver_since"));
        System.out.printf("%-15s %s%n", "Street name:", rsAddress.getString("street_name"));
        System.out.printf("%-15s %s%n", "Street number:", rsAddress.getString("street_number"));
        String apartmentNum = rsAddress.getString("apartment_number");
        System.out.printf("%-15s %s%n", "Apartment num.:", apartmentNum == null ? "N/A" : apartmentNum);
        System.out.printf("%-15s %s%n", "ZIP code:", rsAddress.getString("zip_code"));
        System.out.printf("%-15s %s%n", "City:", rsZipCode.getString("city"));
        System.out.printf("%-15s %s%n", "Country:", rsZipCode.getString("country"));
        System.out.printf("%n");
    }


    public static void createRenter() throws SQLException {
        Connector con = Connector.getInstance();

        System.out.printf("Please enter the required information as prompted");

        // Basic renter information
        String firstName = GetInput.getStringOfLength(25, "First name");
        String lastName = GetInput.getStringOfLength(25, "Last name");
        String telephone = GetInput.getStringOfLength(15, "Telephone num");
        String email = GetInput.getStringOfLength(45, "E-mail");
        String driversLicense = GetInput.getStringOfLength(20, "Drivers license");
        LocalDate driverSince = DateHelper.getValidLocalDateFromUser("Driver since (yyyy-MM-dd)");

        // Address information
        String streetName = GetInput.getStringOfLength(45, "Street name");
        String streetNumber = GetInput.getStringOfLength(5, "Street number");
        System.out.printf("Write N/A as apartment number if not applicable%n");
        String apartmentNumber = GetInput.getStringOfLength(6, "Apartment number");

        // ZIP information
        String zipCode = GetInput.getStringOfLength(10, "ZIP code");
        String city = GetInput.getStringOfLength(85, "City");
        String country = GetInput.getStringOfLength(50, "Country");

        String checkZip = String.format("SELECT * FROM zip_codes WHERE zip = '%s'", zipCode);
        String insertZip = String.format("INSERT INTO zip_codes VALUES ('%s', '%s', '%s')", zipCode, city, country);
        con.insertIfNotExistsString(checkZip, insertZip, "zip");

        String checkAddress = String.format("SELECT * FROM addresses WHERE street_name = '%s' AND street_number = '%s' AND " +
                "apartment_number = '%s'", streetName, streetNumber, apartmentNumber);

        // If apartment number is null it cannot be inserted as a string (with single quotes)
        int addressID = -1;
        String insertAddress = "";
        if(apartmentNumber.equals("N/A")){
            insertAddress = String.format("INSERT INTO addresses VALUES (0, '%s', '%s', NULL, '%s')", streetName, streetNumber, zipCode);
            addressID = con.insertIfNotExistsInt(checkAddress, insertAddress);

        } else{
            insertAddress = String.format("INSERT INTO addresses VALUES (0, '%s', '%s', '%s', '%s')", streetName, streetNumber, apartmentNumber, zipCode);
            addressID = con.insertIfNotExistsInt(checkAddress, insertAddress);
        }

        String renterQuery = String.format("INSERT INTO renters VALUES (0, '%s', '%s', '%s', '%s', '%s', '%s', %d)",
                firstName, lastName, telephone, email, driversLicense, DateHelper.dateToString(driverSince), addressID);
        con.executeUpdate(renterQuery);
    }

    public static int getValidId(int id, Connector con) throws SQLException {
        ResultSet rs = con.executeQuery("SELECT * FROM renters WHERE id = " + id);
        while(rs.next() == false){
            System.out.printf("No renters found with the given ID. Please select an ID from the following list:%n");
            showAllRenters();
            id = GetInput.getIntFromUser("ID");
            rs = con.executeQuery("SELECT * FROM renters WHERE id = " + id);
        }
        return id;
    }
}

