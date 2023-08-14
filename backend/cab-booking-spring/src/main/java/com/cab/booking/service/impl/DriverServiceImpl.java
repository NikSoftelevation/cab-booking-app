package com.cab.booking.service.impl;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.Ride;
import com.cab.booking.exceeption.DriverException;
import com.cab.booking.exceeption.RideException;
import com.cab.booking.jwt.JwtUtil;
import com.cab.booking.repository.DriverRepository;
import com.cab.booking.repository.LicenseRepository;
import com.cab.booking.repository.RideRepository;
import com.cab.booking.repository.VehicleRepository;
import com.cab.booking.request.DriverSignupRequest;
import com.cab.booking.service.Calculators;
import com.cab.booking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private Calculators distanceCalculator;


    @Override
    public Driver registerDriver(DriverSignupRequest request) {
        return null;
    }

    @Override
    public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, double radius, Ride ride) {
        return null;
    }

    @Override
    public Driver findNearestDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude) {
        return null;
    }

    @Override
    public Driver getReqDriverProfile(String jwt) throws DriverException {
        return null;
    }

    @Override
    public Ride getDriversCurrentRide(int driverId) throws RideException {
        return null;
    }

    @Override
    public List<Ride> getAllocatedRides(int driverId) throws RideException {
        return null;
    }

    @Override
    public Driver findDriverByDrierId(int driverId) throws DriverException {
        return null;
    }

    @Override
    public List<Ride> completedRides(int driverId) throws DriverException {
        return null;
    }
}
