package com.example.airline.service.Impl;

import com.example.airline.model.binding.UserRegisterBindingModel;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.model.enums.RoleEnum;
import com.example.airline.repository.RoleRepository;
import com.example.airline.repository.UserRepository;
import com.example.airline.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    private final EmailServiceImpl emailService;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, EmailServiceImpl emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }
    @Override
    public void initUsers() {

        if (this.userRepository.count() != 0) {
            return;
        }

        UserEntity user = new UserEntity()
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setEmail("admin@abv.bg")
                .setPassword(passwordEncoder.encode("1234"))
                .setPhoneNumber("8888")
                .setRoles(roleRepository.findAll());


        this.userRepository.saveAndFlush(user);
    }

    @Override
    public boolean existsByEmail(String value) {
        return userRepository.findByEmail(value).isEmpty();
    }

    @Override
    public boolean existsByPhoneNumber(String value) {
        return userRepository.findByPhoneNumber(value).isEmpty();
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegisterBindingModel.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email used");
        }

        UserEntity user = modelMapper.map(userRegisterBindingModel, UserEntity.class);

        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        user.setRoles(Arrays.asList(roleRepository.findByRole(RoleEnum.USER)));


        this.userRepository.save(user);

        String email = user.getEmail();

        this.emailService.sendRegistrationEmail(email,user.getFirstName());
    }
}






