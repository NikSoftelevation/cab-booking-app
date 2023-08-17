package com.cab.booking.controller;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.Ride;
import com.cab.booking.exceeption.DriverException;
import com.cab.booking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/profile")
    public ResponseEntity<Driver> getDriverProfileHandler(@RequestHeader("Authorization") String jwt) throws DriverException {

        Driver driver = driverService.getReqDriverProfile(jwt);

        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @GetMapping("/{driverId}/current_ride")
    public ResponseEntity<Ride> getDriversCurrentRideHandler(@PathVariable("driverId") int driverId) throws DriverException {

        Ride ride = driverService.getDriversCurrentRide(driverId);
        return new ResponseEntity<>(ride, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{driverId}/allocated")
    public ResponseEntity<List<Ride>> getAllocatedRidesHandler(@PathVariable("driverId") int driverId) throws DriverException {

        List<Ride> allocatedRides = driverService.getAllocatedRides(driverId);

        return new ResponseEntity<>(allocatedRides, HttpStatus.ACCEPTED);
    }

    @GetMapping("/rides/completed")
    public ResponseEntity<List<Ride>> getCompletedRidesHandler(@RequestHeader("Authorization") String jwt) throws DriverException {

        Driver driver = driverService.getReqDriverProfile(jwt);

        List<Ride> completedRides = driverService.completedRides(driver.getId());
        return new ResponseEntity<>(completedRides, HttpStatus.ACCEPTED);
    }

}
