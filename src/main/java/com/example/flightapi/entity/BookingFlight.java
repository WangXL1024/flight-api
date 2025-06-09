package com.example.flightapi.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "booking_flight")
@Getter
@Setter
public class BookingFlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_flight_id")
    private Long bookingFlightId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "flight_type", nullable = false, length = 20)
    private String flightType;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

}    
