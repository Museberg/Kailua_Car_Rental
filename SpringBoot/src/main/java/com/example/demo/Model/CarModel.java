package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CarModel {

    @Id
    private String model;
    private int brandId;

    // Found via join repo
    private String brand_name;

    public CarModel() {
    }

    public CarModel(String model, int brandId, String brand_name) {
        this.model = model;
        this.brandId = brandId;
        this.brand_name = brand_name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brandName) {
        this.brand_name = brandName;
    }
}
