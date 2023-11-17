package com.example.airline.model.dto;

import com.example.airline.model.entity.UserEntity;
import com.example.airline.model.enums.CountryEnum;

import java.math.BigDecimal;

public class UpdateCarDto {

    private String model;

    private String seats;

    private String bag;

    private String doors;

    private String fuelType;

    private BigDecimal price;

    private CountryEnum pickUpAndDropLocation;

    private UserEntity rental;

    public UpdateCarDto() {
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

    public UserEntity getRental() {
        return rental;
    }

    public void setRental(UserEntity rental) {
        this.rental = rental;
    }
}
