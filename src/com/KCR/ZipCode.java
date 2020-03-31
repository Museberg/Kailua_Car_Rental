package com.KCR;

public class ZipCode {
    private String zip;
    private String city;
    private String country;

    public ZipCode(String zip, String city, String country) {
        this.zip = zip;
        this.city = city;
        this.country = country;
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
