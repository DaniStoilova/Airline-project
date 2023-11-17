package com.example.airline.model.dto;

import com.example.airline.model.enums.CountryEnum;
import com.example.airline.model.enums.PropertyType;

import java.math.BigDecimal;

public class UpdateHotelDto {


    private CountryEnum location;

    private PropertyType propertyType;


    private int room;

    private BigDecimal price;

    public UpdateHotelDto() {
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
}
