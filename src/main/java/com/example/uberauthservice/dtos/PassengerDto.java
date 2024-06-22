package com.example.uberauthservice.dtos;

import com.example.uberauthservice.models.Passenger;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private Long id;
    private String name;
    private String email;
    private String password; //encrypted
    private String phone;
    private Date createdAt;


    public static PassengerDto from(Passenger passenger) {
        return PassengerDto.builder()
                .id(passenger.getId())
                .name(passenger.getName())
                .email(passenger.getEmail())
                .password(passenger.getPassword())
                .phone(passenger.getPhone())
                .createdAt(passenger.getCreatedAt())
                .build();
    }
}
