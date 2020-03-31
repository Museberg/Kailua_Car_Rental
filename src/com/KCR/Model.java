package com.KCR;

public class Model {
    private String model;
    private int brandID;

    public Model(String model, int brandID) {
        this.model = model;
        this.brandID = brandID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }
}
