package com.cab.booking.dto;

import com.cab.booking.domain.PaymentDetails;
import com.cab.booking.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RideDto {
    private int id;
    private UserDto userDto;
    private DriverDto driverDto;

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
}
