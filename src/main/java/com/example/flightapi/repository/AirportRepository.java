package com.example.flightapi.repository;


import com.example.flightapi.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByCode(String code);
    List<Airport> findByCity(String city);
}  
