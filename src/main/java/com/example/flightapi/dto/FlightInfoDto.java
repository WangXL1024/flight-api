package com.example.flightapi.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class FlightInfoDto {
    
    private Long flightId;
    private String flightType;
    private BigDecimal price;
}
