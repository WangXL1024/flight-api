package com.example.flightapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportDto {

    private Long airportId;
    private String code;
    private String name;
    private String city;

}    
