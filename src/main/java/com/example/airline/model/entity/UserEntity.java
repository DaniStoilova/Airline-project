package com.example.airline.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
public class UserEntity extends BaseEntity {
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "passenger",fetch = FetchType.EAGER)
    private List<Flight> flights;
    @OneToMany(mappedBy = "rental",fetch = FetchType.EAGER)
    private List<RentACar> cars;
    @OneToMany(mappedBy = "passenger",fetch = FetchType.EAGER)
    private List<Hotel> hotels;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;


    public UserEntity() {
        this.roles = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.hotels = new ArrayList<>();
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public UserEntity setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }
    public UserEntity setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
    public void addRoles(RoleEntity role) {
        this.roles.add(role);
    }

    public List<RentACar> getCars() {
        return cars;
    }

    public void setCars(List<RentACar> cars) {
        this.cars = cars;
    }
}
