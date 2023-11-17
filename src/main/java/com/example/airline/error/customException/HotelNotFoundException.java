package com.example.airline.error.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Hotel was not found.")
public class HotelNotFoundException extends RuntimeException{


        private Long id;

        public HotelNotFoundException(Long id) {
            super("Hotel with ID" + id + "not found");
            this.id = id;
        }

        public Long getId() {
            return id;
        }

}
