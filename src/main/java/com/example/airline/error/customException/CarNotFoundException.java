package com.example.airline.error.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Car was not found.")
public class CarNotFoundException extends RuntimeException{


    private Long id;

    public CarNotFoundException(Long id) {
        super("Car with ID" + id + "not found");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
