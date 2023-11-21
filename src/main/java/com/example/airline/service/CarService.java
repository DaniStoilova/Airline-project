package com.example.airline.service;

import com.example.airline.model.binding.AddCarBindingModel;
import com.example.airline.model.dto.AllCarsDto;
import com.example.airline.model.dto.CarDto;
import com.example.airline.model.dto.UpdateCarDto;
import com.example.airline.model.entity.RentACar;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface CarService {
    void addCarReservation(AddCarBindingModel addCarBindingModel, User user);

    List<AllCarsDto> getAllCars();

    void deleteCar(Long id);

//    BigDecimal getCarByIdAndPrice(Long id);

    Optional<CarDto> getCarById(Long id, User user);

    Optional<UpdateCarDto> getUpdateCar(Long id);

    Optional<RentACar> findById(Long id);

    RentACar updateCar(RentACar rentACar, UpdateCarDto updateCarDto);

    void bookCar(Long id, User user);
}
