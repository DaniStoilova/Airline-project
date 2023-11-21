package com.example.airline.service;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

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



    @BeforeEach
    void setUp() {
        this.passwordEncoder = new Pbkdf2PasswordEncoder();
        this.modelMapper = new ModelMapper();
        this.userService = new UserServiceImpl(userRepository,roleRepository,modelMapper,passwordEncoder,emailService);


    }

    @Test
    void testFindByEmail(){

        String email = "lora@abv.bg";

        UserEntity user = new UserEntity().setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserEntity returned = this.userService.findByEmail(email);

        Assertions.assertEquals(returned.getEmail(), user.getEmail());
    }
}
