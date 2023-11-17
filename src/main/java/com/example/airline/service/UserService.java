package com.example.airline.service;

import com.example.airline.model.binding.UserRegisterBindingModel;
import com.example.airline.model.entity.UserEntity;


public interface UserService {
    boolean existsByEmail(String value);

    boolean existsByPhoneNumber(String value);

    UserEntity findByEmail(String email);

    void register(UserRegisterBindingModel userRegisterBindingModel);
    void initUsers();

}
