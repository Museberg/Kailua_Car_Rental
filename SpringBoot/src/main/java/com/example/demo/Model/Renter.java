package com.example.demo.Model;

public class Renter {
    private int id;
    private String first_name;
    private String last_name;
    private String telephone;
    private String email;
    private String driver_license;
    private String driver_since;
    private String street_name;
    private String street_number;
    private String apartment_number;
    private String zip;
    private String city;
    private String country;

    public Renter(){
    }

    public Renter(int id, String first_name, String last_name, String telephone, String email, String driver_license, String driver_since, String street_name, String apartment_number, String zip, String city, String country) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.telephone = telephone;
        this.email = email;
        this.driver_license = driver_license;
        this.driver_since = driver_since;
        this.street_name = street_name;
        this.apartment_number = apartment_number;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDriver_license() {
        return driver_license;
    }

    public void setDriver_license(String driver_license) {
        this.driver_license = driver_license;
    }

    public String getDriver_since() {
        return driver_since;
    }

    public void setDriver_since(String driver_since) {
        this.driver_since = driver_since;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
