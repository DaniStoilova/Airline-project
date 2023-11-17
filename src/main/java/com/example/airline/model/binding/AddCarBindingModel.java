package com.example.airline.model.binding;


import com.example.airline.model.enums.CountryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

import static com.example.airline.error.InvalidMessage.*;

public class AddCarBindingModel {
    @NotNull(message = "Model must not be empty!")
    @Size(min = 3, message = "Model must be at least 3 characters!")
    private String model;
    @NotBlank(message = "Seats must not be empty!")
    @Positive(message ="Seats must be a positive!")
    private String seats;
    @NotBlank(message = "Bag must not be empty!")
    @Positive(message ="Bag must be a positive!")
    private String bag;
    @NotBlank(message = "Doors must not be empty!")
    @Positive(message ="Doors must be a positive!")
    private String doors;
    @NotBlank(message = "Fuel type must not be empty!")
    private String fuelType;
    @NotNull(message = INVALID_PRICE_EMPTY)
    @Positive(message = INVALID_POSITIVE_PRICE)
    private BigDecimal price;
//    @NotNull(message = INVALID_PRICE_EMPTY)
//    @Positive(message ="Days must be a positive!")
//    private int days;

    @NotNull(message = INVALID_ENUM)
    private CountryEnum pickUpAndDropLocation;

    public AddCarBindingModel() {
    }

//    public int getDays() {
//        return days;
//    }
//
//    public void setDays(int days) {
//        this.days = days;
//    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public CountryEnum getPickUpAndDropLocation() {
        return pickUpAndDropLocation;
    }

    public void setPickUpAndDropLocation(CountryEnum pickUpAndDropLocation) {
        this.pickUpAndDropLocation = pickUpAndDropLocation;
    }
}
