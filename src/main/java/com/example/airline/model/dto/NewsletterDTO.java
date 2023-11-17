package com.example.airline.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class NewsletterDTO {
    @NotBlank(message = "Enter an email!")
    @Email(message = "Enter a valid email!")
    private String email;

    public NewsletterDTO() {
    }

    public NewsletterDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public NewsletterDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
