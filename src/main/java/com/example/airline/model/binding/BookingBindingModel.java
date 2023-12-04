package com.example.airline.model.binding;

import com.example.airline.model.entity.Flight;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import static com.example.airline.error.InvalidMessage.INVALID_EMAIL;


public class BookingBindingModel {

    private Long id;
    @NotNull
    @Size(min = 3,max = 20,message = "Full Name length must be between 3 and 20 characters")
    private String fullName;

    @NotBlank(message = INVALID_EMAIL)
    @Email
    private String email;
    @NotNull
    @Size(min = 3,max = 20,message = "Passport Number length must be between 3 and 20 characters")
    private String passportNumber;

    @NotNull
    @Size(min = 3,max = 20,message = "Booking status length must be between 3 and 20 characters")
    private String bookingStatus;

    @NotNull
    @Size(min = 2,max = 10,message = "Seats length must be between 2 and 10 characters")
    private String seats;

    @NotNull
    @Size(min = 2,max = 20,message = "Baggage length must be between 2 and 20 characters")
    private String baggage;

    @NotNull
    private Flight flight;

    public BookingBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getBaggage() {
        return baggage;
    }

    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
