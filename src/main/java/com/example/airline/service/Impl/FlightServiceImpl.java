package com.example.airline.service.Impl;

import com.example.airline.model.binding.AddFlightBindingModel;
import com.example.airline.model.dto.AllFlightDTO;
import com.example.airline.model.dto.TicketDto;
import com.example.airline.model.dto.UpdateFlightDto;
import com.example.airline.model.entity.Flight;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.model.enums.CountryEnum;
import com.example.airline.repository.FlightRepository;
import com.example.airline.repository.UserRepository;
import com.example.airline.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public FlightServiceImpl(FlightRepository flightRepository, UserRepository userRepository,ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addBook(AddFlightBindingModel addFlightBindingModel, User user) {

        UserEntity pass = userRepository.findByEmail(user.getUsername()).
                orElseThrow();

        Flight flight = modelMapper.map(addFlightBindingModel, Flight.class);

        flight.setPassenger(pass);

        flightRepository.save(flight);


    }

    @Override
    public List<AllFlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(all -> modelMapper.map(all, AllFlightDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<UpdateFlightDto> getUpdateFlight(Long id) {
        return flightRepository.findById(id).map(flight ->
                modelMapper.map(flight, UpdateFlightDto.class));
    }

    @Override
    public Flight updateFlight(Flight update, UpdateFlightDto updateFlightDto) {

        update.setAgeEnum(updateFlightDto.getAgeEnum());
        update.setTripEnum(updateFlightDto.getTripEnum());
        update.setFlightNumber(updateFlightDto.getFlightNumber());
        update.setDestination(updateFlightDto.getDestination());
        update.setDepTime(updateFlightDto.getDepTime());
        update.setClassEnum(updateFlightDto.getClassEnum());
        update.setOrigin(updateFlightDto.getOrigin());
        update.setPrice(updateFlightDto.getPrice());


       return flightRepository.save(update);

    }

    @Override
    public Optional<Flight> findById(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public Optional<TicketDto> getTicketById(Long id,User user) {
        return flightRepository.findById(id).map(ticket -> {
            TicketDto ticketDto = modelMapper.map(ticket, TicketDto.class);

            UserEntity pass = userRepository.findByEmail(user.getUsername()).
                    orElseThrow();

            ticketDto.setFirstName(pass.getFirstName());
            ticketDto.setLastName(pass.getLastName());


            return ticketDto;
        });


    }

    @Override
    public void buyTicket(Long id,User user) {

        flightRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotal(Long id) {
      return flightRepository.findFlightByPrice(id);
    }


    @Override
    public List<Flight> getAllFlightsWithLowPrice(BigDecimal price) {
       return flightRepository.findAllByPriceIsLessThan(BigDecimal.valueOf(50));


    }

    @Override
    public Optional<AllFlightDTO> findFlightById(Long id) {
        return flightRepository.findById(id).map(fl-> modelMapper.map(fl, AllFlightDTO.class));
    }

    @Override
    public List<Flight> getAllFlightsWithOrigin(String origin,String destination) {
        return flightRepository.findAllByOriginAndDestination(CountryEnum.valueOf(origin),CountryEnum.valueOf(destination));

    }



}
