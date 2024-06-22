package com.example.uberauthservice.service;


import com.example.uberauthservice.dtos.PassengerDto;
import com.example.uberauthservice.dtos.PassengerSignupRequestDto;
import com.example.uberauthservice.models.Passenger;
import com.example.uberauthservice.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private PassengerRepository passengerRepository;

    public AuthService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public PassengerDto signupPassenger(PassengerSignupRequestDto request) {

         Passenger passenger = Passenger.builder()
                                        .name(request.getName())
                                        .email(request.getEmail())
                                        .password(request.getPassword())
                                        .phone(request.getPhone()).build();

         Passenger savedPassenger = passengerRepository.save(passenger);

         return PassengerDto.from(savedPassenger);


    }
}
