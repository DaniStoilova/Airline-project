package com.example.airline.service;

import com.example.airline.model.binding.UserRegisterBindingModel;
import com.example.airline.model.dto.UpdateProfileDto;
import com.example.airline.model.dto.UserProfileDto;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.model.enums.RoleEnum;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    boolean existsByEmail(String value);

    boolean existsByPhoneNumber(String value);

    UserEntity findByEmail(String email);

    void register(UserRegisterBindingModel userRegisterBindingModel);
    void initUsers();


    UserProfileDto getLoggedUserDTO(String username);

    Optional<UpdateProfileDto> getUpdateProfile(Long id);

    UserEntity updateProfile(UserEntity user, UpdateProfileDto updateProfile);

    Optional<UserEntity> findById(Long id);

    void deleteById(String username);

    List<String> findAllUser();


    void changeRole(String email, RoleEnum role);
}
