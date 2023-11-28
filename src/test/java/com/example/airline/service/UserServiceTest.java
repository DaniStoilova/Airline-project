package com.example.airline.service;

import com.example.airline.model.dto.UpdateCarDto;
import com.example.airline.model.dto.UpdateProfileDto;
import com.example.airline.model.entity.RentACar;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.repository.RoleRepository;
import com.example.airline.repository.UserRepository;
import com.example.airline.service.Impl.EmailServiceImpl;
import com.example.airline.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private  EmailServiceImpl emailService;
    private UserService userService;

    @Mock
    private  RoleRepository roleRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PasswordEncoder passwordEncoder;



    private UserEntity testUser;


    @BeforeEach
    void setUp() {
        this.passwordEncoder = new Pbkdf2PasswordEncoder();
        this.modelMapper = new ModelMapper();
        this.userService = new UserServiceImpl(userRepository,roleRepository,modelMapper,passwordEncoder,emailService);

        testUser = new UserEntity();
        testUser.setId(1L);
        testUser.setFirstName("Ivan");
        testUser.setPassword("1111");


    }

    @Test
    void testFindByEmail(){

        String email = "lora@abv.bg";

        UserEntity user = new UserEntity().setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserEntity returned = this.userService.findByEmail(email);

        Assertions.assertEquals(returned.getEmail(), user.getEmail());
    }
    @Test
    void testUpdateUser(){

        UpdateProfileDto test = modelMapper.map(testUser, UpdateProfileDto.class);
        test.setFirstName("Lora");

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(testUser);
        UserEntity returned = userService.updateProfile(testUser, test);

        UserEntity expected = new UserEntity();
        expected.setFirstName("Lora");
        expected.setId(2L);

        when(userRepository.findAll()).thenReturn(List.of(expected));
        List<UserEntity> users = userRepository.findAll();

        assertEquals(returned.getFirstName(), users.get(0).getFirstName());

    }


}
