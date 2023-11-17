package com.example.airline.model.binding;
import com.example.airline.validation.annotations.UniqueEmail;
import com.example.airline.validation.annotations.UniquePhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static com.example.airline.error.InvalidMessage.*;

public class UserRegisterBindingModel {

    @NotNull
    @Size(min = 3,max = 20,message = INVALID_FIRST_LENGTH)
    private String firstName;
    @NotNull
    @Size(min = 3,max = 20,message = INVALID_LAST_LENGTH)
    private String lastName;
    @NotBlank(message = INVALID_EMAIL)
    @Email
    @UniqueEmail
    private String email;
    @NotNull
    @Size(min = 3,max = 20,message = INVALID_PASS_LENGTH)
    private String password;
    @NotNull
    @Size(min = 3,max = 20)
    private String confirmPassword;
    @NotBlank(message = INVALID_PHONE_NUMBER)
    @UniquePhoneNumber
    private String phoneNumber;

    public UserRegisterBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
