package com.example.flightapi.controller;

import com.example.flightapi.dto.FlightResponseDto;
import com.example.flightapi.dto.FlightSearchRequest;
import com.example.flightapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/search")
    public List<FlightResponseDto> searchFlights(@RequestBody FlightSearchRequest request) {
        return flightService.searchFlights(request);
    }
    @GetMapping("/{flightId}")
    public ResponseEntity<FlightResponseDto> getFlightById(@PathVariable Long flightId) {
        Optional<FlightResponseDto> flightOptional = flightService.getFlightById(flightId);
        
        return flightOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}  
