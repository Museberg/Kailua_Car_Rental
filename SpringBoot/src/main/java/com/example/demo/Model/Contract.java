package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contract {
    @Id
    private int id;
    private int car_id;
    private String car_brand;
    private String car_model;
    private int renter_id;
    private String start_date;
    private String end_date;
    private int start_km;
    private int max_km;
    private String first_name;
    private String last_name;

    public Contract() {
    }

    public Contract(int id, int car_id, String car_brand, String car_model, int renter_id, String start_date, String end_date, int start_km, int max_km, String first_name, String last_name) {
        this.id = id;
        this.car_id = car_id;
        this.car_brand = car_brand;
        this.car_model = car_model;
        this.renter_id = renter_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_km = start_km;
        this.max_km = max_km;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String carBrand) {
        this.car_brand = carBrand;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String carModel) {
        this.car_model = carModel;
    }

    public int getRenter_id() {
        return renter_id;
    }

    public void setRenter_id(int renterId) {
        this.renter_id = renterId;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String startDate) {
        this.start_date = startDate;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String endDate) {
        this.end_date = endDate;
    }

    public int getStart_km() {
        return start_km;
    }

    public void setStart_km(int startKm) {
        this.start_km = startKm;
    }

    public int getMax_km() {
        return max_km;
    }

    public void setMax_km(int maxKm) {
        this.max_km = maxKm;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
