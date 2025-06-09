package com.example.flightapi.controller;

// import com.example.flightapi.dto.BookingDto;
import com.example.flightapi.dto.BookingRequestDto;
import com.example.flightapi.dto.BookingResponseDto;
import com.example.flightapi.service.BookingService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import javax.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto request) {
        BookingResponseDto response = bookingService.createBooking(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDto>> getBookingsByUserId(@PathVariable Long userId) {
        List<BookingResponseDto> bookings = bookingService.getBookingsByUserId(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    @GetMapping("/{reference}")
    public ResponseEntity<BookingResponseDto> getBookingByReference(@PathVariable String reference) {
        BookingResponseDto response = bookingService.getBookingByReference(reference);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}    
