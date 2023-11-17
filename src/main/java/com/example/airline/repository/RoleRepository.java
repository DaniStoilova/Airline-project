package com.example.airline.repository;

import com.example.airline.model.entity.RoleEntity;
import com.example.airline.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
   RoleEntity findByRole(RoleEnum role);
}
