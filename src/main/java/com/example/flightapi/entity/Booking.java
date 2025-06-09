package com.example.flightapi.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booking")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "reference", nullable = false, length = 20)
    private String reference;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "booking_time", nullable = false)
    private Timestamp bookingTime;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingFlight> bookingFlights = new ArrayList<>();

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passenger> passengers = new ArrayList<>();

 
    // helper methods for relationships
    public void addBookingFlight(BookingFlight bookingFlight) {
        bookingFlights.add(bookingFlight);
        bookingFlight.setBooking(this);
    }

    public void removeBookingFlight(BookingFlight bookingFlight) {
        bookingFlights.remove(bookingFlight);
        bookingFlight.setBooking(null);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        passenger.setBooking(this);
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        passenger.setBooking(null);
    }
}    
