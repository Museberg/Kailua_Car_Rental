package com.example.demo.Model;

public class Address {
    private int id;
    private String street_name;
    private String street_number;
    private String apartment_number;
    private String zip_code;

    public Address(){
    }

    public Address(int id, String street_name, String street_number, String apartment_number, String zip_code) {
        this.id = id;
        this.street_name = street_name;
        this.street_number = street_number;
        this.apartment_number = apartment_number;
        this.zip_code = zip_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getApartment_number() {
        return apartment_number;
    }

    public void setApartment_number(String apartment_number) {
        this.apartment_number = apartment_number;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }
}
