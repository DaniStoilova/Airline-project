package com.example.airline.service.Impl;

import com.example.airline.model.entity.RoleEntity;
import com.example.airline.model.enums.RoleEnum;
import com.example.airline.repository.RoleRepository;
import com.example.airline.service.RoleService;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if (roleRepository.count() == 0) {
            var user = new RoleEntity().setRole(RoleEnum.USER);
            var admin = new RoleEntity().setRole(RoleEnum.ADMIN);

            roleRepository.save(user);
            roleRepository.save(admin);
        }
    }
}