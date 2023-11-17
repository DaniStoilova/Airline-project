package com.example.airline.repository;

import com.example.airline.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {

    Optional<Hotel> getHotelById(Long id);
}
