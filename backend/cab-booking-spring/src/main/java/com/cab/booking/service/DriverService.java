package com.cab.booking.service;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.Ride;
import com.cab.booking.exceeption.DriverException;
import com.cab.booking.exceeption.RideException;
import com.cab.booking.request.DriverSignupRequest;

import java.util.List;

public interface DriverService {

    public Driver registerDriver(DriverSignupRequest request);

    public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, Ride ride);

    public Driver findNearestDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude);

    public Driver getReqDriverProfile(String jwt) throws DriverException;

    public Ride getDriversCurrentRide(int driverId) throws RideException, DriverException;

    public List<Ride> getAllocatedRides(int driverId) throws RideException;

    public Driver findDriverByDriverId(int driverId) throws DriverException;

    public List<Ride> completedRides(int driverId) throws DriverException;
}
