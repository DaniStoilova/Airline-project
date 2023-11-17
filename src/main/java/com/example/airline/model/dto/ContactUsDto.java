package com.example.airline.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import static com.example.airline.error.InvalidMessage.*;

public class ContactUsDto {

    @NotBlank(message = INVALID_EMAIL)
    @Email
    private String email;

    @NotBlank(message = INVALID_FULL_NAME_EMPTY)
    private String fullName;

    @NotBlank(message = INVALID_PHONE_NUMBER)
    private String phoneNumber;
    @NotBlank(message = INVALID_MESSAGE_EMPTY)
    private String message;

    public ContactUsDto() {
    }

    public String getEmail() {
        return email;
    }

    public ContactUsDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public ContactUsDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ContactUsDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ContactUsDto setMessage(String message) {
        this.message = message;
        return this;
    }
}
