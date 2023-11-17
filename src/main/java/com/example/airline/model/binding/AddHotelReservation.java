package com.example.airline.model.binding;

import com.example.airline.model.entity.UserEntity;
import com.example.airline.model.enums.CountryEnum;
import com.example.airline.model.enums.PropertyType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

import static com.example.airline.error.InvalidMessage.INVALID_ENUM;
import static com.example.airline.error.InvalidMessage.INVALID_PRICE_EMPTY;

public class AddHotelReservation {

    @NotNull(message = INVALID_ENUM)
    private CountryEnum location;

    @NotNull(message = INVALID_ENUM)
    private PropertyType propertyType;

    @NotNull
    @Positive(message ="Rooms must be a positive!")
    private int room;

    @NotNull(message = INVALID_PRICE_EMPTY)
    @Positive(message ="Price must be a positive!")
    private BigDecimal price;


    private UserEntity passenger;

    public AddHotelReservation() {
    }

    public UserEntity getPassenger() {
        return passenger;
    }

    public void setPassenger(UserEntity passenger) {
        this.passenger = passenger;
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
