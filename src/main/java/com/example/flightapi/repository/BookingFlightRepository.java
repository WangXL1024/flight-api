package com.example.flightapi.repository;

import com.example.flightapi.entity.BookingFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingFlightRepository extends JpaRepository<BookingFlight, Long> {
}
