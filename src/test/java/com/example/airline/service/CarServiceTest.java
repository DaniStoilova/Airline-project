package com.example.airline.service;

import com.example.airline.model.dto.AllCarsDto;
import com.example.airline.model.dto.UpdateCarDto;
import com.example.airline.model.entity.RentACar;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.repository.RentCarRepository;
import com.example.airline.repository.UserRepository;
import com.example.airline.service.Impl.CarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarService carService;

    @Mock
    private RentCarRepository rentCarRepository;

    @Mock
    private ModelMapper modelMapper;


    private RentACar testCar;

    private UserEntity testUser;

    @BeforeEach
    void setUp(){
        this.modelMapper = new ModelMapper();
        this.carService = new CarServiceImpl(userRepository,modelMapper,rentCarRepository);

        testCar = new RentACar();
        testCar.setId(2L);
        testCar.setModel("Fiat");

        testUser = new UserEntity();
        testUser.setEmail("lora@lora.bg");


    }


    @Test
    void testUpdateCar(){

        UpdateCarDto test = modelMapper.map(testCar, UpdateCarDto.class);
        test.setModel("Citroen");

        when(rentCarRepository.save(Mockito.any(RentACar.class))).thenReturn(testCar);
        RentACar returned = this.carService.updateCar(testCar, test);

        RentACar expected = new RentACar();
        expected.setModel("Citroen");
        expected.setId(2L);

        when(rentCarRepository.findAll()).thenReturn(List.of(expected));
        List<RentACar> cars = this.rentCarRepository.findAll();

        assertEquals(returned.getModel(), cars.get(0).getModel());

    }
    @Test
    void deleteCarByIdTest() {

        RentACar car = new RentACar();
        car.setId(2L);

        carService.deleteCar(car.getId());


        verify(rentCarRepository, times(1)).deleteById(eq(car.getId()));
    }
    @Test
    void testAllCars() {
        AllCarsDto allCarsDto = new AllCarsDto();
        allCarsDto.setPrice(BigDecimal.valueOf(55));
        allCarsDto.setModel("Fiat");
        allCarsDto.setId(1L);

        AllCarsDto allCarsDto2 = new AllCarsDto();
        allCarsDto2.setPrice(BigDecimal.valueOf(66));
        allCarsDto2.setModel("Ford");
        allCarsDto2.setId(2L);

        RentACar car = new RentACar();
        car.setPrice(BigDecimal.valueOf(55));
        car.setModel("Fiat");
        car.setId(1L);

        RentACar car2 = new RentACar();
        car2.setPrice(BigDecimal.valueOf(66));
        car2.setModel("Ford");
        car2.setId(2L);

        when(rentCarRepository.findAll()).thenReturn(List.of(car, car2));
        Assertions.assertEquals(this.carService.getAllCars().get(0).getModel(), allCarsDto.getModel());
        Assertions.assertEquals(this.carService.getAllCars().get(1).getModel(),allCarsDto2.getModel());
        Assertions.assertEquals(this.carService.getAllCars().get(0).getPrice(), allCarsDto.getPrice());
        Assertions.assertEquals(this.carService.getAllCars().get(1).getPrice(),allCarsDto2.getPrice());
        Assertions.assertEquals(this.carService.getAllCars().get(0).getId(), allCarsDto.getId());
        Assertions.assertEquals(this.carService.getAllCars().get(1).getId(),allCarsDto2.getId());


    }
    @Test
    void findByCarIdTest() {

        when(rentCarRepository.findById(1L)).thenReturn(Optional.of(testCar));

        assertEquals(testCar, this.carService.findById(1L).get());
    }


}
