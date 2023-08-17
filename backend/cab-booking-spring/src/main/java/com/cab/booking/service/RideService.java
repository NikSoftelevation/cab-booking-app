package com.cab.booking.service;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.Ride;
import com.cab.booking.domain.User;
import com.cab.booking.exceeption.DriverException;
import com.cab.booking.exceeption.RideException;
import com.cab.booking.request.RideRequest;

public interface RideService {

    public Ride requestRide(RideRequest rideRequest, User user) throws DriverException;

    public Ride createRideRequest(User user, Driver nearestDriver, double pickupLatitude, double pickupLongitude, double destinationLongitude, double destinationLatitude, String pickupArea, String destinationArea);

    public void acceptRide(int rideId) throws RideException;

    public void declineRide(int rideId, int driverId) throws RideException;

    public void startRide(int rideId, int otp) throws RideException;

    public void completeRide(int rideId) throws RideException;

    public void cancelRide(int rideId) throws RideException;

    public Ride findRideById(int rideId) throws RideException;


}
