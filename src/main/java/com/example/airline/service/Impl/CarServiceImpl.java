package com.example.airline.service.Impl;

import com.example.airline.model.binding.AddCarBindingModel;
import com.example.airline.model.dto.AllCarsDto;
import com.example.airline.model.dto.CarDto;
import com.example.airline.model.dto.UpdateCarDto;
import com.example.airline.model.entity.RentACar;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.repository.RentCarRepository;
import com.example.airline.repository.UserRepository;
import com.example.airline.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final RentCarRepository rentCarRepository;




    public CarServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RentCarRepository rentCarRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.rentCarRepository = rentCarRepository;

    }

    @Override
    public void addCarReservation(AddCarBindingModel addCarBindingModel, User user) {

        UserEntity pass = userRepository.findByEmail(user.getUsername()).
                orElseThrow();

        RentACar car = modelMapper.map(addCarBindingModel,RentACar.class );

        car.setRental(pass);

        rentCarRepository.save(car);

    }

    @Override
    public List<AllCarsDto> getAllCars() {
        return rentCarRepository.findAll().stream()
                .map(all -> modelMapper.map(all, AllCarsDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Long id) {
        rentCarRepository.deleteById(id);
    }


    @Override
    public Optional<CarDto> getCarById(Long id, User user) {
        return rentCarRepository.findById(id).map(car -> {
            CarDto carDto = modelMapper.map(car, CarDto.class);


            UserEntity pass = userRepository.findByEmail(user.getUsername()).
                    orElseThrow();

            carDto.setFirstName(pass.getFirstName());
            carDto.setLastName(pass.getLastName());
            carDto.setPhoneNumber(pass.getPhoneNumber());
            carDto.setDate(LocalDate.now());


            return carDto;
        });
    }



    @Override
    public Optional<UpdateCarDto> getUpdateCar(Long id) {
        return rentCarRepository.findById(id).map(car->modelMapper.map(car,UpdateCarDto.class));
    }

    @Override
    public Optional<RentACar> findById(Long id) {
        return rentCarRepository.findById(id);
    }

    @Override
    public RentACar updateCar(RentACar rentACar, UpdateCarDto updateCarDto) {

        rentACar.setModel(updateCarDto.getModel());
        rentACar.setBag(updateCarDto.getBag());
        rentACar.setDoors(updateCarDto.getDoors());
        rentACar.setPrice(updateCarDto.getPrice());
        rentACar.setFuelType(updateCarDto.getFuelType());
        rentACar.setSeats(updateCarDto.getSeats());
        rentACar.setPickUpAndDropLocation(updateCarDto.getPickUpAndDropLocation());

       return rentCarRepository.save(rentACar);

    }

    @Override
    public void bookCar(Long id, User user) {
        rentCarRepository.deleteById(id);
    }

}
