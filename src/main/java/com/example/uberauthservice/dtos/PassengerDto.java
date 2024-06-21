package com.example.uberauthservice.dtos;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private String id;
    private String email;
    private String password; //encrypted
    private String phone;
    private Date createdAt;
}
