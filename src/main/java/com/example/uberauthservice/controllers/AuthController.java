package com.example.uberauthservice.controllers;


import com.example.uberauthservice.dtos.PassengerDto;
import com.example.uberauthservice.dtos.PassengerSignupRequestDto;
import com.example.uberauthservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("passenger/signup")
    public ResponseEntity<PassengerDto> passengerSignup(@RequestBody PassengerSignupRequestDto request){
        PassengerDto passenger = authService.signupPassenger(request);
        return new ResponseEntity<>(passenger, HttpStatus.CREATED);
    }
}
