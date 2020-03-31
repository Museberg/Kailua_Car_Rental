package com.KCR;

import java.util.Date;

public class Car {
    private static enum CarType {
        FAMILY,
        LUXURY,
        SPORT
    }
    private static enum GearType {
        MANUAL,
        AUTOMATIC
    }
    private int id;
    private String registrationNumber;
    private Date firstRegistration;
    private String fuelType;
    private int odometer;
    private String model;
    private CarType carType;
    private GearType gearType;
    private boolean airConditioning;
    private int ccm;
    private int hp;
    private String seatType;
    private int seatNumber;
    private boolean cruiseControl;


    public Car(int id, String registrationNumber, Date firstRegistration, String fuelType, int odometer, String model, CarType carType,
               GearType gearType, boolean airConditioning, int ccm, int hp, String seatType, int seatNumber, boolean cruiseControl) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.firstRegistration = firstRegistration;
        this.fuelType = fuelType;
        this.odometer = odometer;
        this.model = model;
        this.carType = carType;
        this.gearType = gearType;
        this.airConditioning = airConditioning;
        this.ccm = ccm;
        this.hp = hp;
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.cruiseControl = cruiseControl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(Date firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public int getCcm() {
        return ccm;
    }

    public void setCcm(int ccm) {
        this.ccm = ccm;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isCruiseControl() {
        return cruiseControl;
    }

    public void setCruiseControl(boolean cruiseControl) {
        this.cruiseControl = cruiseControl;
    }
}
