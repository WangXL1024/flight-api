package com.example.flightapi.service;


import com.example.flightapi.dto.AirportDto;
import com.example.flightapi.entity.Airport;
import com.example.flightapi.repository.AirportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<AirportDto> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AirportDto getAirportByCode(String code) {
        Optional<Airport> airportOptional = airportRepository.findByCode(code);
        return airportOptional.map(this::convertToDto).orElse(null);
    }

    public List<AirportDto> getAirportsByCity(String city) {
        List<Airport> airports = airportRepository.findByCity(city);
        return airports.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AirportDto convertToDto(Airport airport) {
        return modelMapper.map(airport, AirportDto.class);
    }
}    
