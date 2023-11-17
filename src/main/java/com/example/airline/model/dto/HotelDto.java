package com.example.airline.model.dto;

import com.example.airline.model.enums.CountryEnum;
import com.example.airline.model.enums.PropertyType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HotelDto {

    private Long id;

    private CountryEnum location;

    private PropertyType propertyType;

    private LocalDate date;


    private int room;

    private BigDecimal price;


    private String firstName;

    private String lastName;

    private String phoneNumber;


    public HotelDto() {
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
