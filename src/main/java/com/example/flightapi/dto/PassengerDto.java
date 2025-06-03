package com.example.flightapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerDto {

    private Long passengerId;
    private Long bookingId;
    private String firstName;
    private String lastName;
    private String email;
}   
