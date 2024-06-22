package com.example.uberauthservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonIgnoreProperties({"driver","passenger"})
public class Booking extends BaseModel {

//    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
//    private Review review;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    private Long totalDistance;

    //Many to one --> one Booking can have many driver
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;


}
