package com.example.airline.model.dto;

import com.example.airline.model.enums.AgeEnum;
import com.example.airline.model.enums.ClassEnum;
import com.example.airline.model.enums.CountryEnum;
import com.example.airline.model.enums.TripEnum;

import java.time.LocalDateTime;

public class AllFlightDTO {

    private String flightNumber;

    private Long id;

    private TripEnum tripEnum;

    private CountryEnum origin;

    private CountryEnum destination;

    private LocalDateTime depTime;

    private ClassEnum classEnum;

    private AgeEnum ageEnum;

    private double price;

    public AllFlightDTO() {
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
