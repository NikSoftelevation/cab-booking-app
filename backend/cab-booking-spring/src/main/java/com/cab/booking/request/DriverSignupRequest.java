package com.cab.booking.request;

import com.cab.booking.domain.License;
import com.cab.booking.domain.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverSignupRequest {

    private String name;
    private String email;
    private String mobile;
    private String password;
    private double longitude;
    private double latitude;
    private License license;
    private Vehicle vehicle;
}
