package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Contract {
    @Id
    private int id;
    private String carBrand;
    private String carModel;
    private int renterId;
    private Date startDate;
    private Date endDate;
    private int startKm;
    private int maxKm;

    public Contract() {
    }

    public Contract(int id, String carBrand, String carModel, int renterId, Date startDate, Date endDate, int startKm, int maxKm) {
        this.id = id;
        this.carBrand = carBrand;
        this.carModel= carModel;
        this.renterId = renterId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startKm = startKm;
        this.maxKm = maxKm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStartKm() {
        return startKm;
    }

    public void setStartKm(int startKm) {
        this.startKm = startKm;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }
}
