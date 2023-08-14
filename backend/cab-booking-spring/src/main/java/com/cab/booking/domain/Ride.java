package com.cab.booking.domain;

import com.cab.booking.enums.RideStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Driver driver;


    @JsonIgnore
    private List<Integer> declinedDrivers = new ArrayList<>();

    private double pickupLatitude;

    private double pickupLongitude;

    private double destinationLatitude;

    private double destinationLongitude;

    private String pickupArea;

    private String destinationArea;

    private double distance;

    private long duration;

    private RideStatus rideStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private double fare;

    private int otp;

    @Embedded
    private PaymentDetails paymentDetails=new PaymentDetails();
}