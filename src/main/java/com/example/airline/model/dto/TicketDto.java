package com.example.airline.model.dto;

import com.example.airline.model.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String origin;

    private String destination;

    private String flightNumber;

//    private String seat;

    private LocalDateTime depTime;
    private BigDecimal price;


    private UserEntity passenger;

    public TicketDto() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public UserEntity getPassenger() {
        return passenger;
    }

    public void setPassenger(UserEntity passenger) {
        this.passenger = passenger;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepTime() {
        return depTime;
    }

    public void setDepTime(LocalDateTime depTime) {
        this.depTime = depTime;
    }
}
