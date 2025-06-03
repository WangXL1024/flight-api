package com.example.flightapi.dto;



import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingFlightDto {

    private Long bookingFlightId;
    private Long flightId;
    private String flightType;
    private BigDecimal price;
    private FlightResponseDto flight;


}    
