package com.example.airline.service;


import com.example.airline.model.dto.UpdateFlightDto;
import com.example.airline.model.entity.Flight;
import com.example.airline.model.enums.*;
import com.example.airline.repository.FlightRepository;
import com.example.airline.repository.UserRepository;
import com.example.airline.service.Impl.FlightServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;
    @Mock
    private FlightService flightService;

    @Mock
    private ModelMapper modelMapper;
    private Flight testFlight;


    @BeforeEach
    void setUp() {
        this.modelMapper = new ModelMapper();
        this.flightService = new FlightServiceImpl(flightRepository,userRepository,modelMapper);

        testFlight = new Flight();
        testFlight.setFlightNumber("345");
        testFlight.setId(1L);



    }

    @Test
    void testUpdateFlights(){
        UpdateFlightDto test = modelMapper.map(testFlight, UpdateFlightDto.class);
        test.setFlightNumber("new234");

        when(flightRepository.save(Mockito.any(Flight.class))).thenReturn(testFlight);
        Flight returned = this.flightService.updateFlight(testFlight, test);

        Flight expected = new Flight();
        expected.setFlightNumber("new234");
        expected.setId(1L);

        when(flightRepository.findAll()).thenReturn(List.of(expected));
        List<Flight> flights = this.flightRepository.findAll();

        assertEquals(returned.getFlightNumber(), flights.get(0).getFlightNumber());


    }
    @Test
    void findByIdTest() {

        Flight flight = new Flight();
                flight.setFlightNumber("3333");
                flight.setPrice(BigDecimal.valueOf(55));
                flight.setOrigin(CountryEnum.Berlin);
                flight.setDestination(CountryEnum.Alicante);
                flight.setClassEnum(ClassEnum.Economy);
                flight.setAgeEnum(AgeEnum.Adult);
                flight.setTripEnum(TripEnum.One);
                flight.setId(1L);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        assertEquals(flight, this.flightService.findById(1L).get());
    }
    @Test
    void deleteByIdTest() {

        Flight flight = new Flight();
        flight.setId(1L);

        flightService.deleteFlight(flight.getId());


        verify(flightRepository, times(1)).deleteById(eq(flight.getId()));
    }


}
