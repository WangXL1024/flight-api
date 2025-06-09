package com.example.flightapi.repository;

import com.example.flightapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

        @Query("SELECT f FROM Flight f " +
                "JOIN FETCH f.departureAirport da " +
                "JOIN FETCH f.destinationAirport dest " +
                "WHERE da.code = :departureCode " +
                "AND dest.code = :destinationCode " +
                "AND f.departureDate = :departureDate")
        List<Flight> findByDepartureAirportAndDestinationAirportAndDepartureDate(
                @Param("departureCode") String departureCode,
                @Param("destinationCode") String destinationCode,
                @Param("departureDate") LocalDate departureDate);


        List<Flight> findByDepartureAirport_AirportIdAndDestinationAirport_AirportIdAndDepartureDate(
        Long departureAirportId, Long destinationAirportId, LocalDate departureDate);

            @Query("SELECT f FROM Flight f " +
            "JOIN FETCH f.departureAirport " +
            "JOIN FETCH f.destinationAirport " +
            "WHERE f.flightId = :flightId")
        Optional<Flight> findByIdWithAirports(@Param("flightId") Long flightId);

}  
