package com.example.flightapi.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightSearchRequest {

    private String tripType; // 单程还是往返
    private String departureAirportCode;
    private String destinationAirportCode;
    private LocalDate departureDate;
    private LocalDate returnDate;

}  
