package com.example.flightapi.service;

import com.example.flightapi.dto.FlightSearchRequest;
import com.example.flightapi.dto.AirportDto;
import com.example.flightapi.dto.FlightResponseDto;
import com.example.flightapi.entity.Airport;
import com.example.flightapi.entity.Flight;
import com.example.flightapi.repository.FlightRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;

 
    public List<FlightResponseDto> searchFlights(FlightSearchRequest request) {
        List<FlightResponseDto> results = new ArrayList<>();

        // 查询去程航班
        List<Flight> outboundFlights = flightRepository.findByDepartureAirportAndDestinationAirportAndDepartureDate(
                request.getDepartureAirportCode(),
                request.getDestinationAirportCode(),
                request.getDepartureDate());

        // 转换去程航班为响应DTO
        for (Flight flight : outboundFlights) {
            FlightResponseDto response = convertToResponse(flight);
            response.setFlightType("outbound");
            results.add(response);
        }

        // 如果是往返，查询返程航班
        if ("ROUND_TRIP".equalsIgnoreCase(request.getTripType()) && request.getReturnDate() != null) {
            List<Flight> returnFlights = flightRepository.findByDepartureAirportAndDestinationAirportAndDepartureDate(
                    request.getDestinationAirportCode(),
                    request.getDepartureAirportCode(),
                    request.getReturnDate());

            // 转换返程航班为响应DTO
            for (Flight flight : returnFlights) {
                FlightResponseDto response = convertToResponse(flight);
                response.setFlightType("return");
                results.add(response);
            }
        }

        return results;
    }

    public Optional<FlightResponseDto> getFlightById(Long flightId) {
        return flightRepository.findByIdWithAirports(flightId)
                .map(this::convertToResponse);
    }

    private FlightResponseDto convertToResponse(Flight flight) {
        FlightResponseDto response = new FlightResponseDto();
        response.setFlightId(flight.getFlightId());
        response.setFlightNumber(flight.getFlightNumber());
        response.setDepartureDate(flight.getDepartureDate());
        response.setDepartureTime(flight.getDepartureTime());
        response.setPrice(flight.getPrice());

        // 设置出发机场信息
        if (flight.getDepartureAirport() != null) {
            response.setDepartureAirport(convertToDto(flight.getDepartureAirport()));
        }

        // 设置目的机场信息
        if (flight.getDestinationAirport() != null) {
            response.setDestinationAirport(convertToDto(flight.getDestinationAirport()));
        }

        return response;
    }

    private AirportDto convertToDto(Airport airport) {
        return modelMapper.map(airport, AirportDto.class);
    }
}  
