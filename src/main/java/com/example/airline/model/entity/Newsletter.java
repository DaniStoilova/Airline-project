package com.example.airline.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "newsletter")
public class Newsletter extends BaseEntity {

        @Column(nullable = false)
        private String email;

        public Newsletter() {
        }

        public String getEmail() {
            return email;
        }

        public Newsletter setEmail(String email) {
            this.email = email;
            return this;
        }
    }

