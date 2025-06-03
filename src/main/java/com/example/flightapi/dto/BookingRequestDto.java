package com.example.flightapi.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestDto {

    private Long userId;
    private List<FlightInfoDto> flights;
    private List<PassengerDto> passengers;

} 
