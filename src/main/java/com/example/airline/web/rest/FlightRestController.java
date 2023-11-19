package com.example.airline.web.rest;

import com.example.airline.model.dto.AllFlightDTO;
import com.example.airline.model.entity.Flight;
import com.example.airline.service.FlightService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightRestController {

    private final FlightService flightService;


    public FlightRestController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping()
    public ResponseEntity<List<AllFlightDTO>> getAllFlights(){
        return ResponseEntity.ok(this.flightService.getAllFlights());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AllFlightDTO> findFlightById(@PathVariable("id")Long id){
       Optional<AllFlightDTO> optionalFlight = flightService.findFlightById(id);
        return optionalFlight.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }






}
