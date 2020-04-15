package com.KCR;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class ContractController {
    public static void showAllContracts() throws SQLException {
        Connector con = Connector.getInstance();
        ResultSet rs = con.executeQuery("SELECT * FROM contracts");

        while(rs.next()){
            int renter_id = rs.getInt("renter_id");
            int car_id = rs.getInt("car_id");
            String renterQuery = String.format("SELECT * FROM contracts JOIN renters ON contracts.renter_id = renters.id WHERE renters.id = '%d'", renter_id);
            String carQuery = String.format("SELECT * FROM contracts JOIN cars ON contracts.car_id = cars.id WHERE cars.id = '%d'", car_id);
            ResultSet rsRenters = con.executeQuery(renterQuery);
            rsRenters.next();
            ResultSet rsCars = con.executeQuery(carQuery);
            rsCars.next();


            System.out.printf("ID: %d, ", rs.getInt("id"));
            System.out.printf("Renter: %s %s, ", rsRenters.getString("first_name"), rsRenters.getString("last_name"));
            System.out.printf("Car: %s %s, ", rsCars.getString("model"), rsCars.getString("registration_number"));
            Date startDate = rs.getTimestamp("start_date");
            Date endDate = rs.getTimestamp("end_date");
            System.out.printf("Timespan: %s - %s (%d days)%n", DateHelper.dateToString(startDate), DateHelper.dateToString(endDate),
                    DateHelper.getDays(startDate, endDate));
        }
        System.out.printf("%n");
    }

    public static void showContract(int id) throws SQLException {
        Connector con = Connector.getInstance();
        id = getValidId(id, con);
        ResultSet rs = con.executeQuery("SELECT * FROM contracts WHERE id = " + id);
        rs.next();

        int renter_id = rs.getInt("renter_id");
        int car_id = rs.getInt("car_id");

        String renterQuery = String.format("SELECT * FROM contracts JOIN renters ON contracts.renter_id = renters.id WHERE renters.id = '%d'", renter_id);
        ResultSet rsRenters = con.executeQuery(renterQuery);
        rsRenters.next();

        String modelQuery = String.format("SELECT * FROM contracts JOIN cars ON contracts.car_id = cars.id WHERE cars.id = '%d'", car_id);
        ResultSet rsModels = con.executeQuery(modelQuery);
        rsModels.next();
        String model = rsModels.getString("model");

        String brandQuery = String.format("SELECT * FROM models JOIN brands ON brands.id = models.brand_id WHERE models.model = '%s'", model);
        ResultSet rsBrand = con.executeQuery(brandQuery);
        rsBrand.next();

        System.out.println("Contracts:");
        System.out.printf("%-15s %d%n", "ID:", rs.getInt("id"));
        System.out.printf("%-15s %s%n", "Car reg. num.:", rsModels.getString("registration_number"));
        System.out.printf("%-15s %s %s%n", "Car:", rsBrand.getString("brand_name"), rsModels.getString("model"));
        System.out.printf("%-15s %s %s%n", "Renter", rsRenters.getString("first_name"), rsRenters.getString("last_name"));
        Date startDate = rs.getTimestamp("start_date");
        Date endDate = rs.getTimestamp("end_date");
        System.out.printf("%-15s %s - %s (%d days)%n", "Timespan:", DateHelper.dateToString(startDate), DateHelper.dateToString(endDate),
                DateHelper.getDays(startDate, endDate));
        System.out.printf("%-15s %d%n", "Start KM:", rs.getInt("start_km"));
        System.out.printf("%-15s %d (%d KM)%n", "Max KM:", rs.getInt("max_km"), rs.getInt("max_km") - rs.getInt("start_km"));
        System.out.printf("%n");
    }

    public static void createContract() throws SQLException, ParseException {
        Connector con = Connector.getInstance();

        System.out.printf("Please enter the required information as prompted");

        Date startDate = DateHelper.getValidDateFromUser("Start date");
        Date endDate = DateHelper.getValidDateFromUser("End date");
        while(DateHelper.getDays(startDate, endDate) < 0){
            System.out.println("Dates are not valid. Please try again!");
            startDate = DateHelper.getValidDateFromUser("Start date");
            endDate = DateHelper.getValidDateFromUser("End date");
        }

        System.out.println("Please select a car by its ID");
        CarController.showAllCars();
        int carID = GetInput.getIntFromUser("Car ID");
        carID = getValidId(carID, con);

        System.out.println("Please select a renter by its ID");
        RenterController.showAllRenters();
        int renterID = GetInput.getIntFromUser("Renter ID");
        renterID = RenterController.getValidId(renterID, con);

        String carKMQuery = String.format("SELECT odometer FROM cars WHERE cars.id = '%d'", carID);
        ResultSet rsKM = con.executeQuery(carKMQuery);
        rsKM.next();
        int startKM = rsKM.getInt("odometer");

        int maxKM = GetInput.getIntAbove(startKM,"Max KM (minimum " + startKM + ")");

        String contractQuery = String.format("INSERT INTO contracts VALUES (0, '%d', '%d', '%s', '%s', '%d', '%d')",
                carID, renterID, DateHelper.dateToString(startDate), DateHelper.dateToString(endDate), startKM, maxKM);

        con.executeUpdate(contractQuery);
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
