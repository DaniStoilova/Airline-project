package com.example.airline.model.entity;

import com.example.airline.model.enums.CountryEnum;
import com.example.airline.model.enums.PropertyType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="hotels")
public class Hotel extends BaseEntity{

//    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CountryEnum location;
//    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Column(nullable = false)
    private int room;
    @Column(nullable = false)
    private BigDecimal price;



//    @Column(nullable = false)
//    private LocalDate checkIn;
//    @Column(nullable = false)
//    private LocalDate checkIOut;



    public UserEntity getPassenger() {
        return passenger;
    }

    public void setPassenger(UserEntity passenger) {
        this.passenger = passenger;
    }

    @ManyToOne
    private UserEntity passenger;

    public Hotel() {
    }

    public CountryEnum getLocation() {
        return location;
    }

    public void setLocation(CountryEnum location) {
        this.location = location;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

//    public LocalDate getCheckIn() {
//        return checkIn;
//    }
//
//    public void setCheckIn(LocalDate checkIn) {
//        this.checkIn = checkIn;
//    }
//
//    public LocalDate getCheckIOut() {
//        return checkIOut;
//    }
//
//    public void setCheckIOut(LocalDate checkIOut) {
//        this.checkIOut = checkIOut;
//    }
}
