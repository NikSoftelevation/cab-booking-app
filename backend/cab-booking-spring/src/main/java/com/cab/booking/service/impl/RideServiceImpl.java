package com.cab.booking.service.impl;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.Ride;
import com.cab.booking.domain.User;
import com.cab.booking.enums.RideStatus;
import com.cab.booking.exceeption.DriverException;
import com.cab.booking.exceeption.RideException;
import com.cab.booking.repository.DriverRepository;
import com.cab.booking.repository.RideRepository;
import com.cab.booking.request.RideRequest;
import com.cab.booking.service.Calculators;
import com.cab.booking.service.DriverService;
import com.cab.booking.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    private DriverService driverService;
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private Calculators calculators;

    @Override
    public Ride requestRide(RideRequest rideRequest, User user) throws DriverException {

        double pickupLongitude = rideRequest.getPickupLongitude();

        double pickupLatitude = rideRequest.getPickupLatitude();

        double destinationLatitude = rideRequest.getDestinationLatitude();

        double destinationLongitude = rideRequest.getDestinationLongitude();

        String destinationArea = rideRequest.getDestinationArea();

        String pickupArea = rideRequest.getPickupArea();

        Ride existingRide = new Ride();

        List<Driver> availableDrivers = driverService.getAvailableDrivers(pickupLatitude, pickupLongitude, existingRide);

        Driver nearestDriver = driverService.findNearestDriver(availableDrivers, pickupLatitude, pickupLongitude);

        if (nearestDriver == null) {
            throw new DriverException("Driver not available");
        }
        System.out.println("Duration ------ Before Ride");


        Ride ride = createRideRequest(user, nearestDriver, pickupLatitude, pickupLongitude, destinationLongitude, destinationLatitude, pickupArea, destinationArea);
        return ride;
    }

    @Override
    public Ride createRideRequest(User user, Driver nearestDriver, double pickupLatitude, double pickupLongitude, double destinationLongitude, double destinationLatitude, String pickupArea, String destinationArea) {

        Ride ride = new Ride();

        ride.setDriver(nearestDriver);
        ride.setUser(user);
        ride.setPickupLatitude(pickupLatitude);
        ride.setPickupLongitude(pickupLongitude);
        ride.setDestinationLatitude(destinationLatitude);
        ride.setDestinationLongitude(destinationLongitude);
        ride.setRideStatus(RideStatus.REQUESTED);
        ride.setPickupArea(pickupArea);
        ride.setDestinationArea(destinationArea);

        System.out.println("----- a - " + pickupLatitude);

        return rideRepository.save(ride);
    }

    @Override
    public void acceptRide(int rideId) throws RideException {

        Ride rideById = findRideById(rideId);
        rideById.setRideStatus(RideStatus.ACCEPTED);

        Driver driver = rideById.getDriver();
        driver.setCurrentRide(rideById);

        Random random = new Random();
        int otp = random.nextInt(9000) + 1000;
        rideById.setOtp(otp);

        driverRepository.save(driver);
        rideRepository.save(rideById);
    }


    @Override
    public void declineRide(int rideId, int driverId) throws RideException {

        Ride rideById = findRideById(rideId);

        System.out.println(rideById.getId());

        rideById.getDeclinedDrivers().add(driverId);

        System.out.println(rideById.getId() + "-" + rideById.getDeclinedDrivers());

        List<Driver> availableDrivers = driverService.getAvailableDrivers(rideById.getPickupLatitude(), rideById.getPickupLongitude(), rideById);

        Driver nearestDriver = driverService.findNearestDriver(availableDrivers, rideById.getPickupLatitude(), rideById.getPickupLongitude());

        rideById.setDriver(nearestDriver);

        rideRepository.save(rideById);
    }

    @Override
    public void startRide(int rideId, int otp) throws RideException {
        Ride rideById = findRideById(rideId);

        if (otp != rideById.getOtp()) {

            throw new RideException("Please provide a valid otp");
        }
        rideById.setRideStatus(RideStatus.STARTED);
        rideById.setStartTime(LocalDateTime.now());

        rideRepository.save(rideById);
    }

    @Override
    public void completeRide(int rideId) throws RideException {

        Ride rideById = findRideById(rideId);

        rideById.setRideStatus(RideStatus.COMPLETED);
        rideById.setEndTime(LocalDateTime.now());

        double distance = calculators.calculateDistance(rideById.getDestinationLatitude(), rideById.getDestinationLongitude(), rideById.getPickupLatitude(), rideById.getPickupLongitude());

        LocalDateTime startTime = rideById.getStartTime();
        LocalDateTime endTime = rideById.getEndTime();
        Duration duration = Duration.between(startTime, endTime);
        long milliSecond = duration.toMillis();

        System.out.println("duration " + milliSecond);

        double fare = calculators.calculateFare(distance);
        rideById.setDistance(Math.round(distance * 100.0) / 100.0);
        rideById.setFare((int) Math.round(fare));
        rideById.setEndTime(LocalDateTime.now());

        Driver driver = rideById.getDriver();
        driver.getRides().add(rideById);
        driver.setCurrentRide(null);


        Integer driversRevenue = (int) (driver.getTotalRevenue() + Math.round(fare * 0.8));

        driver.setTotalRevenue(driversRevenue);

        System.out.println("Drivers Revenue --" + driversRevenue);

        driverRepository.save(driver);
        rideRepository.save(rideById);
    }

    @Override
    public void cancelRide(int rideId) throws RideException {
        Ride ride = findRideById(rideId);

        ride.setRideStatus(RideStatus.CANCELLED);
        rideRepository.save(ride);
    }

    @Override
    public Ride findRideById(int rideId) throws RideException {

        Optional<Ride> optionalRide = rideRepository.findById(rideId);

        if (optionalRide.isPresent()) {
            return optionalRide.get();
        }
        throw new RideException("no ride found with this id " + rideId);
    }
}
