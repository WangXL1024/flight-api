package com.example.flightapi.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "airport")
@Getter
@Setter
public class Airport {

    @Id
    @Column(name = "airport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "departureAirport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> departureFlights = new ArrayList<>();

    @OneToMany(mappedBy = "destinationAirport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> destinationFlights = new ArrayList<>();

}  
