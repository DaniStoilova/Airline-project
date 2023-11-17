package com.example.airline.model.dto;

import com.example.airline.model.enums.CountryEnum;

import java.math.BigDecimal;

public class AllCarsDto {

    private Long id;
    private String model;

    private String seats;

    private String bag;

    private String doors;

    private String fuelType;

    private BigDecimal price;

//    private int days;


    private CountryEnum pickUpAndDropLocation;

    public AllCarsDto() {

    }

//    public int getDays() {
//        return days;
//    }
//
//    public void setDays(int days) {
//        this.days = days;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
