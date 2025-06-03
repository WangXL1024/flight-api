package com.example.flightapi.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDto {

    private Long bookingId;
    private Long userId;
    private String reference;
    private String status;
    private Timestamp bookingTime;
    private BigDecimal totalPrice;
    private List<BookingFlightDto> bookingFlights;
    private List<PassengerDto> passengers;
    
    
    

    

}



 
