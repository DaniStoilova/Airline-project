package com.example.airline.repository;

import com.example.airline.model.entity.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentCarRepository extends JpaRepository<RentACar,Long> {

//    @Query("SELECT SUM(r.price * r.days) FROM RentACar r WHERE r.id = :id ")
//    BigDecimal findRentACarByPrice(@Param("id")Long id);

//    @Query("SELECT SUM(r.price * r.days) FROM RentACar r")
//    BigDecimal findRentACarByPrice();





}
