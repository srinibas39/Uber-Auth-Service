package com.example.uberauthservice.controllers;


import com.example.uberauthservice.dtos.PassengerSignupRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @PostMapping("passenger/signup")
    public ResponseEntity<?> passengerSignup(@RequestBody PassengerSignupRequestDto request){
        System.out.println(request.getName());
        return null;
    }
}
