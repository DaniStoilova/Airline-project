package com.example.airline.repository;

import com.example.airline.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByFullName(String fullName);

    List<Booking> findByEmail(String email);

}
