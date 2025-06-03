package com.example.flightapi.controller;

import com.example.flightapi.dto.AirportDto;
import com.example.flightapi.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<AirportDto> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{code}")
    public AirportDto getAirportByCode(@PathVariable String code) {
        return airportService.getAirportByCode(code);
    }

    @GetMapping("/city/{city}")
    public List<AirportDto> getAirportsByCity(@PathVariable String city) {
        return airportService.getAirportsByCity(city);
    }
}    
