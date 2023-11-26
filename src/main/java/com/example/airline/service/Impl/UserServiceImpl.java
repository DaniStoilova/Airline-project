package com.example.airline.service.Impl;

import com.example.airline.model.binding.UserRegisterBindingModel;
import com.example.airline.model.dto.UpdateProfileDto;
import com.example.airline.model.dto.UserProfileDto;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.model.enums.RoleEnum;
import com.example.airline.repository.RoleRepository;
import com.example.airline.repository.UserRepository;
import com.example.airline.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final EmailServiceImpl emailService;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, EmailServiceImpl emailService) {
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
    public UserProfileDto getLoggedUserDTO(String username) {
        return this.modelMapper.map(this.getLoggedUser(username), UserProfileDto.class);
    }

    @Override
    public Optional<UpdateProfileDto> getUpdateProfile(Long id) {
        return userRepository.findById(id).map(p->modelMapper.map(p,UpdateProfileDto.class));
    }

    @Override
    public UserEntity updateProfile(UserEntity user, UpdateProfileDto updateProfile) {

        user.setFirstName(updateProfile.getFirstName());
        user.setLastName(updateProfile.getLastName());
        user.setEmail(updateProfile.getEmail());
        user.setPhoneNumber(updateProfile.getPhoneNumber());

        if (updateProfile.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateProfile.getPassword()));
        }


        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(String username) {
        Optional<UserEntity> user = this.userRepository.findByEmail(username);
        this.userRepository.deleteById(user.get().getId());
    }

    public Optional<UserEntity> getLoggedUser(String username){
        return userRepository.findByEmail(username);

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






