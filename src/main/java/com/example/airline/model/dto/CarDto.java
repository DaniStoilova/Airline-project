package com.example.airline.model.dto;


import com.example.airline.model.enums.CountryEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CarDto {

    private Long id;


    private String model;

    private String seats;

    private String bag;

    private String doors;

    private String fuelType;

    private BigDecimal price;

    private CountryEnum pickUpAndDropLocation;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private LocalDate date;

    public CarDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getBag() {
        return bag;
    }

    public void setBag(String bag) {
        this.bag = bag;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CountryEnum getPickUpAndDropLocation() {
        return pickUpAndDropLocation;
    }

    public void setPickUpAndDropLocation(CountryEnum pickUpAndDropLocation) {
        this.pickUpAndDropLocation = pickUpAndDropLocation;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
