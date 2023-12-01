package com.example.airline.model.entity;

import com.example.airline.model.enums.CountryEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="cars")
public class RentACar extends BaseEntity {
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String seats;
    @Column(nullable = false)
    private String bag;
    @Column(nullable = false)
    private String doors;
    @Column(nullable = false)
    private String fuelType;
    @Column(nullable = false)
    private BigDecimal price;
//    @Column(nullable = false)
//    private int days;

//    public int getDays() {
//        return days;
//    }
//
//    public void setDays(int days) {
//        this.days = days;
//    }


    @Enumerated(EnumType.STRING)
    private CountryEnum pickUpAndDropLocation;

    @ManyToOne
    private UserEntity rental;

    public RentACar() {
    }
    public UserEntity getRental() {
        return rental;
    }

    public void setRental(UserEntity rental) {
        this.rental = rental;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
