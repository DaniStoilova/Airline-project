package com.example.airline.service;

import com.example.airline.model.binding.AddFlightBindingModel;
import com.example.airline.model.dto.AllFlightDTO;
import com.example.airline.model.dto.TicketDto;
import com.example.airline.model.dto.UpdateFlightDto;
import com.example.airline.model.entity.Flight;
import org.springframework.security.core.userdetails.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    void addBook(AddFlightBindingModel addFlightBindingModel, User user);

    List<AllFlightDTO> getAllFlights();

    Optional<UpdateFlightDto> getUpdateFlight(Long id);

    void updateFlight(Flight update, UpdateFlightDto updateFlightDto);

    Optional<Flight> findById(Long id);

    void deleteFlight(Long id);

    Optional<TicketDto> getTicketById(Long id,User user);

    void buyTicket(Long id,User user);

    BigDecimal getTotal(Long id);

    public List<Flight>  getAllFlightsWithLowPrice(BigDecimal price);


    Optional<AllFlightDTO> findFlightById(Long id);
}
