package com.cab.booking.dto;

import com.cab.booking.domain.Vehicle;
import com.cab.booking.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverDto {

    private int id;
    private String name;
    private String email;
    private String mobile;
    private double rating;
    private double latitude;
    private double longitude;
    private UserRole userRole;
    private Vehicle vehicle;
}