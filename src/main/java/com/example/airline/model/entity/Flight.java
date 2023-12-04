package com.example.airline.model.entity;


import com.example.airline.model.enums.AgeEnum;
import com.example.airline.model.enums.ClassEnum;
import com.example.airline.model.enums.CountryEnum;
import com.example.airline.model.enums.TripEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="flights")
public class Flight extends BaseEntity {

    @Column(nullable = false)
    private String flightNumber;

    @Enumerated(EnumType.STRING)
    private TripEnum tripEnum;
    @Enumerated(EnumType.STRING)
    private CountryEnum origin;
    @Enumerated(EnumType.STRING)
    private CountryEnum destination;
    @Column(nullable = false)
    private LocalDateTime depTime;
    @Enumerated(EnumType.STRING)
    private AgeEnum ageEnum;
    @Enumerated(EnumType.STRING)
    private ClassEnum classEnum;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne
    private UserEntity passenger;

    @OneToMany(mappedBy = "flight",fetch = FetchType.LAZY)
    private Set<Booking> bookings = new HashSet<>();


    public Flight() {
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
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

    public AgeEnum getAgeEnum() {
        return ageEnum;
    }

    public void setAgeEnum(AgeEnum ageEnum) {
        this.ageEnum = ageEnum;
    }

    public ClassEnum getClassEnum() {
        return classEnum;
    }

    public void setClassEnum(ClassEnum classEnum) {
        this.classEnum = classEnum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UserEntity getPassenger() {
        return passenger;
    }

    public void setPassenger(UserEntity passenger) {
        this.passenger = passenger;
    }


}
