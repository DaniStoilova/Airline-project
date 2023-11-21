package com.example.airline.service;


import com.example.airline.config.security.AirlineUserDetailService;
import com.example.airline.model.entity.RoleEntity;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.model.enums.RoleEnum;
import com.example.airline.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AirlineUserDetailServiceTest {
    @Mock
    private UserRepository mockUserRepository;

    private AirlineUserDetailService test;

    @BeforeEach
    void setUp() {
        test = new AirlineUserDetailService(
                mockUserRepository
        );
    }

   @Test
    void testLoadUserByUsername_UserExists() {

        UserEntity testUser =
                new UserEntity().
                        setEmail("lora@lora.bg").
                        setPassword("1111").
                        setRoles(
                                List.of(
                                        new RoleEntity().setRole(RoleEnum.ADMIN),
                                        new RoleEntity().setRole(RoleEnum.USER)
                                )
                        );

        when(mockUserRepository.findByEmail(testUser.getEmail())).
                thenReturn(Optional.of(testUser));

       UserDetails userDetails = test.loadUserByUsername(testUser.getEmail());

        Assertions.assertEquals(testUser.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(),userDetails.getPassword());



        var authorities = userDetails.getAuthorities();

        Assertions.assertEquals(2, authorities.size());

        var authoritiesIter = authorities.iterator();

        Assertions.assertEquals("ROLE_" + RoleEnum.ADMIN.name(),
                authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + RoleEnum.USER.name(),
                authoritiesIter.next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> test.loadUserByUsername("non-lora@lora.bg")
        );
    }

}
