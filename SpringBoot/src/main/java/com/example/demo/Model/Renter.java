package com.example.demo.Model;

import java.util.Date;

public class Renter {
    private int renterId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String driverLicense;
    private Date driverSince;
    private String address;

    public Renter(){
    }

    public Renter(int renterId, String firstName, String lastName, String phone, String email, String driverLicense, Date driverSince, String address) {
        this.renterId = renterId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.driverLicense = driverLicense;
        this.driverSince = driverSince;
        this.address = address;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public Date getDriverSince() {
        return driverSince;
    }

    public void setDriverSince(Date driverSince) {
        this.driverSince = driverSince;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
