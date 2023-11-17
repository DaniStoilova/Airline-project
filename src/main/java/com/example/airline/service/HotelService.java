package com.example.airline.service;

import com.example.airline.model.binding.AddHotelReservation;
import com.example.airline.model.dto.AllHotelDto;
import com.example.airline.model.dto.HotelDto;
import com.example.airline.model.dto.UpdateHotelDto;
import com.example.airline.model.entity.Hotel;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    void addHotelReservation(AddHotelReservation addHotelReservation, User user);

    List<AllHotelDto> getAllHotel();

    Optional<HotelDto> getHotelById(Long id, User user);

    void buyHotel(Long id, User user);

    void deleteHotel(Long id);

    Optional<UpdateHotelDto> getUpdateHotel(Long id);

    Optional<Hotel> findById(Long id);

    void updateHotel(Hotel hotel, UpdateHotelDto updateHotelDto);

//    UserEntity buyHotel(Long id, User user);
}
