package com.example.flightapi.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String country;
    private String phone;
    private Date createdAt;

    // constructors
    public UserResponse() {
    }

    public UserResponse(Long userId, String email, String firstName, String lastName, String country, String phone, Date createdAt) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.phone = phone;
        this.createdAt = createdAt;
    }
}  
