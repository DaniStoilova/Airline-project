package com.example.airline.service.Impl;

import com.example.airline.model.binding.AddHotelReservation;
import com.example.airline.model.dto.AllHotelDto;
import com.example.airline.model.dto.HotelDto;
import com.example.airline.model.dto.UpdateHotelDto;
import com.example.airline.model.entity.Hotel;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.repository.HotelRepository;
import com.example.airline.repository.UserRepository;
import com.example.airline.service.HotelService;
import com.example.airline.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private UserRepository userRepository;

    private HotelRepository hotelRepository;

    private UserService userService;

    private ModelMapper modelMapper;

    public HotelServiceImpl(UserRepository userRepository, HotelRepository hotelRepository, UserService userService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addHotelReservation(AddHotelReservation addHotelReservation, User user) {
        UserEntity pass = userRepository.findByEmail(user.getUsername()).
                orElseThrow();

        Hotel hotel = modelMapper.map(addHotelReservation, Hotel.class );

       hotel.setPassenger(pass);

        hotelRepository.save(hotel);
    }

    @Override
    public List<AllHotelDto> getAllHotel() {
        return hotelRepository.findAll().stream()
                .map(h -> modelMapper.map(h, AllHotelDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<HotelDto> getHotelById(Long id, User user) {
        return hotelRepository.findById(id).map(ticket -> {
            HotelDto hotelDto = modelMapper.map(ticket, HotelDto.class);


            UserEntity pass = userRepository.findByEmail(user.getUsername()).
                    orElseThrow();

            hotelDto.setFirstName(pass.getFirstName());
            hotelDto.setLastName(pass.getLastName());
            hotelDto.setPhoneNumber(pass.getPhoneNumber());
            hotelDto.setDate(LocalDate.now());


            return hotelDto;
        });
    }

    @Override
    public void buyHotel(Long id, User user) {
        hotelRepository.deleteById(id);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Optional<UpdateHotelDto> getUpdateHotel(Long id) {
        return hotelRepository.findById(id).map(hotel ->
                modelMapper.map(hotel, UpdateHotelDto.class));
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public void updateHotel(Hotel hotel, UpdateHotelDto updateHotelDto) {

        hotel.setLocation(updateHotelDto.getLocation());
        hotel.setPrice(updateHotelDto.getPrice());
        hotel.setRoom(updateHotelDto.getRoom());
        hotel.setPropertyType(updateHotelDto.getPropertyType());


        hotelRepository.save(hotel);
    }


//    @Override
//    public UserEntity buyHotel(Long id, User user) {
//        Hotel hotel = this.hotelRepository.getHotelById(id).orElseThrow();
//        UserEntity userEntity = this.userService.findByEmail(user.getUsername());
//        hotel.setPassenger(userEntity);
//        this.hotelRepository.save(hotel);
//        return hotel.getPassenger();
//        }


}
