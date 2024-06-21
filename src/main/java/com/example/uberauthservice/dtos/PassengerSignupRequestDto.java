package com.example.uberauthservice.dtos;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSignupRequestDto {

    private String name;
    private String phone;
    private String email;
    private String password;
}
