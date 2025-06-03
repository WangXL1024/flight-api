package com.example.flightapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String phone;

  
}  
