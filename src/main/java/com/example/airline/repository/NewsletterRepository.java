package com.example.airline.repository;

import com.example.airline.model.entity.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter,Long> {
    Optional<Newsletter> findByEmail(String email);
}
