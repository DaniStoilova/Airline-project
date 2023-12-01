package com.example.airline.repository;


import com.example.airline.model.dto.AllFlightDTO;
import com.example.airline.model.entity.Flight;
import com.example.airline.model.enums.CountryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {


    @Query("SELECT SUM(f.price) FROM Flight f WHERE f.id = :id ")
    BigDecimal findFlightByPrice(@Param("id")Long id);


    List<Flight> findAllByPriceIsLessThan(BigDecimal price);

    List<Flight> findAllByOriginAndDestination(CountryEnum origin,CountryEnum destination);


}
