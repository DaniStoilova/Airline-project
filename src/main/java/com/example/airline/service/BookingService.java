package com.example.airline.service;

import com.example.airline.model.binding.BookingBindingModel;
import com.example.airline.model.dto.AllBookings;
import com.example.airline.model.entity.Booking;
import com.example.airline.model.entity.UserEntity;
import org.springframework.security.core.userdetails.User;


import java.util.List;
import java.util.Optional;


public interface BookingService {
    void addBooking(BookingBindingModel bookingBindingModel);

    List<AllBookings> getAllBookings();

    void cancell(Long id);


    List<Booking> getBook(String fullName);


}
