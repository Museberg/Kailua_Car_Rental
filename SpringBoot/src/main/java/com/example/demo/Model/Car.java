package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    private int id;
    private String registration_number;
    private String first_registration;
    private String fuel_type;
    private int odometer;
    private String model;
    private String car_type;
    private String gear_type;
    private boolean air_conditioning;
    private Integer ccm;
    private int hp;
    private String seat_type;
    private int seat_number;
    private boolean cruise_control;

    // Found via a join in repo
    private String brand;


    public Car() {
    }

    public Car(int id, String registration_number, String first_registration, String fuel_type, int odometer, String model,
               String car_type, String gear_type, boolean air_conditioning, Integer ccm, int hp, String seat_type,
               int seat_number, boolean cruise_control, String brand) {
        this.id = id;
        this.registration_number = registration_number;
        this.first_registration = first_registration;
        this.fuel_type = fuel_type;
        this.odometer = odometer;
        this.model = model;
        this.car_type = car_type;
        this.gear_type = gear_type;
        this.air_conditioning = air_conditioning;
        this.ccm = ccm;
        this.hp = hp;
        this.seat_type = seat_type;
        this.seat_number = seat_number;
        this.cruise_control = cruise_control;
        this.brand = brand;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getGear_type() {
        return gear_type;
    }

    public void setGear_type(String gear_type) {
        this.gear_type = gear_type;
    }

    public boolean isAir_conditioning() {
        return air_conditioning;
    }

    public void setAir_conditioning(boolean air_conditioning) {
        this.air_conditioning = air_conditioning;
    }

    public int getCcm() {
        return ccm == null ? 0 : ccm;
    }

    public void setCcm(Integer ccm) {
        this.ccm = ccm;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public boolean isCruise_control() {
        return cruise_control;
    }

    public void setCruise_control(boolean cruise_control) {
        this.cruise_control = cruise_control;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getId() {
        return id;
    }

    public String getFirst_registration() {
        return first_registration;
    }

    public void setFirst_registration(String first_registration) {
        this.first_registration = first_registration;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
