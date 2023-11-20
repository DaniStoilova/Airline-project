package com.example.airline.model.binding;

import com.example.airline.model.entity.UserEntity;
import com.example.airline.model.enums.AgeEnum;
import com.example.airline.model.enums.ClassEnum;
import com.example.airline.model.enums.CountryEnum;
import com.example.airline.model.enums.TripEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.example.airline.error.InvalidMessage.*;

public class AddFlightBindingModel {

    @NotNull
    @Size(min=4,max=10, message = INVALID_FLIGHT_NUMBER)
    private String flightNumber;

    private Long id;

    @NotNull(message = INVALID_ENUM)
    private TripEnum tripEnum;
    @NotNull(message = INVALID_ENUM)
    private CountryEnum origin;
    @NotNull(message = INVALID_ENUM)
    private CountryEnum destination;

    @NotNull(message = INVALID_DATE_EMPTY )
    @Future(message = INVALID_DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime depTime;

   @NotNull(message = INVALID_ENUM)
    private ClassEnum classEnum;

    @NotNull(message = INVALID_ENUM)
    private AgeEnum ageEnum;
    @NotNull(message = INVALID_PRICE_EMPTY)
    @Positive(message = INVALID_POSITIVE_PRICE)
    private double price;

    public UserEntity getPassenger() {
        return passenger;
    }

    public void setPassenger(UserEntity passenger) {
        this.passenger = passenger;
    }

    private UserEntity passenger;

    public AddFlightBindingModel() {
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TripEnum getTripEnum() {
        return tripEnum;
    }

    public void setTripEnum(TripEnum tripEnum) {
        this.tripEnum = tripEnum;
    }

    public CountryEnum getOrigin() {
        return origin;
    }

    public void setOrigin(CountryEnum origin) {
        this.origin = origin;
    }

    public CountryEnum getDestination() {
        return destination;
    }

    public void setDestination(CountryEnum destination) {
        this.destination = destination;
    }


    public LocalDateTime getDepTime() {
        return depTime;
    }

    public void setDepTime(LocalDateTime depTime) {
        this.depTime = depTime;
    }

    public ClassEnum getClassEnum() {
        return classEnum;
    }

    public void setClassEnum(ClassEnum classEnum) {
        this.classEnum = classEnum;
    }

    public AgeEnum getAgeEnum() {
        return ageEnum;
    }

    public void setAgeEnum(AgeEnum ageEnum) {
        this.ageEnum = ageEnum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
