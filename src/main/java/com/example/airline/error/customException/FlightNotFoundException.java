package com.example.airline.error.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException{

    private final Long id;

    private static final String MESSAGE = "Flight not found";

    public FlightNotFoundException(Long id) {
        super(MESSAGE);
        this.id = id;

    }

    public Long getId() {
        return id;
    }
}
