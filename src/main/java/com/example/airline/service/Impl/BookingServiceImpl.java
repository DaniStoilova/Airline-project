package com.example.airline.service.Impl;

import com.example.airline.model.binding.BookingBindingModel;
import com.example.airline.model.dto.AllBookings;
import com.example.airline.model.entity.Booking;
import com.example.airline.model.entity.Flight;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.repository.BookingRepository;
import com.example.airline.repository.FlightRepository;
import com.example.airline.service.BookingService;
import com.example.airline.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;


@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;

    private FlightRepository flightRepository;

    private FlightService flightService;

    private ModelMapper modelMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, FlightRepository flightRepository, FlightService flightService, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.flightService = flightService;

        this.modelMapper = modelMapper;
    }

    @Override
    public void addBooking(BookingBindingModel bookingBindingModel) {

        Booking booking = modelMapper.map(bookingBindingModel, Booking.class);

        booking.setId(bookingBindingModel.getId());
        booking.setFlight(bookingBindingModel.getFlight());


        bookingRepository.save(booking);


    }

    @Override
    public List<AllBookings> getAllBookings(User user) {
        return bookingRepository.findByEmail(user.getUsername()).stream()
                .map(all -> modelMapper.map(all, AllBookings.class)).collect(Collectors.toList());
    }

    @Override
    public void cancell(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> getBook(String fullName) {
        return bookingRepository.findByFullName(fullName);
    }



}
