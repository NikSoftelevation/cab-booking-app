package com.cab.booking.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RideRequest {

    private double pickupLongitude;
    private double pickupLatitude;
    private double destinationLongitude;
    private double destinationLatitude;
    private String pickupArea;
    private String destinationArea;
}
