package com.example.flightapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightResponseDto {

    private Long flightId;
    private String flightNumber;
    private AirportDto departureAirport;
    private AirportDto destinationAirport;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private BigDecimal price;
    private String flightType;


    
}    
