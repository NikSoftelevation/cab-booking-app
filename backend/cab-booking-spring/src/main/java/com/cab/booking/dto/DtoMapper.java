package com.cab.booking.dto;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.Ride;
import com.cab.booking.domain.User;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper {

    public static DriverDto toDriverDto(Driver driver) {

        DriverDto driverDto = new DriverDto();
        driverDto.setEmail(driver.getEmail());
        driverDto.setId(driver.getId());
        driverDto.setLatitude(driver.getLatitude());
        driverDto.setLongitude(driver.getLongitude());
        driverDto.setMobile(driver.getMobile());
        driverDto.setName(driver.getName());
        driverDto.setRating(driver.getRating());
        driverDto.setUserRole(driverDto.getUserRole());
        driverDto.setVehicle(driver.getVehicle());

        return driverDto;
    }

    public static UserDto toUserDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setMobile(user.getMobile());
        userDto.setName(user.getFullName());

        return userDto;
    }

    public static RideDto toRideDto(Ride ride) {

        DriverDto driverDto = toDriverDto(ride.getDriver());

        UserDto userDto = toUserDto(ride.getUser());

        RideDto rideDto = new RideDto();
        rideDto.setDestinationLatitude(ride.getDestinationLatitude());
        rideDto.setDestinationLongitude(ride.getDestinationLongitude());
        rideDto.setDistance(ride.getDistance());
        rideDto.setDriverDto(driverDto);
        rideDto.setDuration(ride.getDuration());
        rideDto.setEndTime(ride.getEndTime());
        rideDto.setFare(ride.getFare());
        rideDto.setId(ride.getId());
        rideDto.setPickupLongitude(ride.getPickupLongitude());
        rideDto.setPickupLatitude(ride.getPickupLatitude());
        rideDto.setStartTime(ride.getStartTime());
        rideDto.setRideStatus(ride.getRideStatus());
        rideDto.setUserDto(userDto);
        rideDto.setPickupArea(ride.getPickupArea());
        rideDto.setDestinationArea(ride.getDestinationArea());
        rideDto.setOtp(ride.getOtp());

        return rideDto;
    }
}
