package com.example.uberauthservice.service;


import com.example.uberauthservice.dtos.PassengerDto;
import com.example.uberauthservice.dtos.PassengerSignupRequestDto;
import com.example.uberauthservice.models.Passenger;
import com.example.uberauthservice.repositories.PassengerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder encoder;
    private final PassengerRepository passengerRepository;

    public AuthService(PassengerRepository passengerRepository, PasswordEncoder encoder) {
        this.passengerRepository = passengerRepository;
        this.encoder = encoder;
    }

    public PassengerDto signupPassenger(PassengerSignupRequestDto request) {

         Passenger passenger = Passenger.builder()
                                        .name(request.getName())
                                        .email(request.getEmail())
                                        .password(encoder.encode(request.getPassword()))
                                        .phone(request.getPhone()).build();

         Passenger savedPassenger = passengerRepository.save(passenger);

         return PassengerDto.from(savedPassenger);


    }
}
