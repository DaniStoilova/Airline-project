package com.example.airline.repository;


import com.example.airline.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhoneNumber(String value);

    @Query("SELECT (u.email) FROM UserEntity u ORDER by u.email ")
    List<String> findAllUsers();



}
