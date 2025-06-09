package com.example.flightapi.service;

import com.example.flightapi.dto.AirportDto;
import com.example.flightapi.dto.BookingRequestDto;
import com.example.flightapi.dto.BookingResponseDto;
import com.example.flightapi.dto.FlightInfoDto;
import com.example.flightapi.dto.FlightResponseDto;
import com.example.flightapi.dto.PassengerDto;
import com.example.flightapi.entity.*;
import com.example.flightapi.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public BookingService(BookingRepository bookingRepository, 
                          UserRepository userRepository,
                          FlightRepository flightRepository,
                          ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto request) {
        // 1. 验证用户是否存在
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("用户不存在"));

        // 2. 创建订单
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setReference(generateBookingReference());
        booking.setStatus("PENDING"); // 初始状态为待处理
        booking.setBookingTime(Timestamp.valueOf(LocalDateTime.now()));
        
        // 3. 计算总价
        BigDecimal totalPrice = calculateTotalPrice(request);
        booking.setTotalPrice(totalPrice);

        // 4. 添加航班信息
        addFlightInformation(booking, request);

        // 5. 添加乘客信息
        addPassengerInformation(booking, request);

        // 6. 保存订单
        Booking savedBooking = bookingRepository.save(booking);

        // 7. 转换为响应DTO
        return convertToResponseDto(savedBooking);
    }

    private String generateBookingReference() {
        // 生成唯一的订单参考号
        return UUID.randomUUID().toString().substring(0, 10).toUpperCase();
    }

    private BigDecimal calculateTotalPrice(BookingRequestDto request) {
        return request.getFlights().stream()
                .map(FlightInfoDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void addFlightInformation(Booking booking, BookingRequestDto request) {
        for (FlightInfoDto flightInfo : request.getFlights()) {
            Flight flight = flightRepository.findById(flightInfo.getFlightId())
                    .orElseThrow(() -> new IllegalArgumentException("航班不存在"));

            BookingFlight bookingFlight = new BookingFlight();
            bookingFlight.setFlight(flight);
            bookingFlight.setFlightType(flightInfo.getFlightType());
            bookingFlight.setPrice(flightInfo.getPrice());
            
            booking.addBookingFlight(bookingFlight);
        }
    }

    private void addPassengerInformation(Booking booking, BookingRequestDto request) {
        for (PassengerDto passengerDto : request.getPassengers()) {
            Passenger passenger = new Passenger();
            passenger.setFirstName(passengerDto.getFirstName());
            passenger.setLastName(passengerDto.getLastName());
            passenger.setEmail(passengerDto.getEmail());
            
            booking.addPassenger(passenger);
        }
    }

    private BookingResponseDto convertToResponseDto(Booking booking) {
        BookingResponseDto response = modelMapper.map(booking, BookingResponseDto.class);
        
        // 处理航班信息
        List<FlightResponseDto> flightResponses = new ArrayList<>();
        for (BookingFlight bookingFlight : booking.getBookingFlights()) {
            Flight flight = bookingFlight.getFlight();
            FlightResponseDto flightResponse = new FlightResponseDto();
            
            flightResponse.setFlightId(flight.getFlightId());
            flightResponse.setFlightNumber(flight.getFlightNumber());
            flightResponse.setDepartureAirport(convertToDto(flight.getDepartureAirport(),AirportDto.class));
            flightResponse.setDepartureDate(flight.getDepartureDate());
            flightResponse.setDepartureTime(flight.getDepartureTime());
            flightResponse.setFlightType(bookingFlight.getFlightType());
            flightResponse.setPrice(bookingFlight.getPrice());
            
            flightResponses.add(flightResponse);
        }
        // response.setFlights(flightResponses);
        
        // 处理乘客信息
        List<PassengerDto> passengerResponses = new ArrayList<>();
        for (Passenger passenger : booking.getPassengers()) {
            PassengerDto passengerResponse = modelMapper.map(passenger, PassengerDto.class);
            passengerResponses.add(passengerResponse);
        }
        response.setPassengers(passengerResponses);
        
        return response;
    }

    public BookingResponseDto getBookingByReference(String reference) {
        Booking booking = bookingRepository.findByReference(reference)
                .orElseThrow(() -> new IllegalArgumentException("订单不存在"));
        return convertToResponseDto(booking);
    }

    public List<BookingResponseDto> getBookingsByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }

        List<Booking> bookings = bookingRepository.findByUser_UserId(userId);
        return bookings.stream()
                .map(booking -> convertToDto(booking, BookingResponseDto.class))
                .collect(Collectors.toList());
    }
    
    private <T> T convertToDto(Object source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

}    
