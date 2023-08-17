package com.cab.booking.service.impl;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.License;
import com.cab.booking.domain.Ride;
import com.cab.booking.domain.Vehicle;
import com.cab.booking.enums.RideStatus;
import com.cab.booking.enums.UserRole;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Driver registerDriver(DriverSignupRequest driverSignupRequest) {

        License license = driverSignupRequest.getLicense();

        Vehicle vehicle = driverSignupRequest.getVehicle();

        License createdLicense = new License();

        createdLicense.setLicenseState(license.getLicenseState());
        createdLicense.setLicenseNumber(license.getLicenseNumber());
        createdLicense.setLicenseExpirationDate(license.getLicenseExpirationDate());
        createdLicense.setId(license.getId());

        License savedLicense = licenseRepository.save(createdLicense);

        Vehicle createdVehicle = new Vehicle();
        createdVehicle.setCapacity(vehicle.getCapacity());
        createdVehicle.setColor(vehicle.getColor());
        createdVehicle.setId(vehicle.getId());
        createdVehicle.setLicensePlate(vehicle.getLicensePlate());
        createdVehicle.setMake(vehicle.getMake());
        createdVehicle.setModel(vehicle.getModel());
        createdVehicle.setYear(vehicle.getYear());

        Vehicle savedVehicle = vehicleRepository.save(createdVehicle);

        Driver driver = new Driver();

        String encodedPassword = bCryptPasswordEncoder.encode(driverSignupRequest.getPassword());

        driver.setEmail(driverSignupRequest.getEmail());
        driver.setName(driverSignupRequest.getName());
        driver.setPassword(encodedPassword);
        driver.setMobile(driverSignupRequest.getMobile());
        driver.setLicense(savedLicense);
        driver.setUserRole(UserRole.DRIVER);

        driver.setLatitude(driverSignupRequest.getLatitude());
        driver.setLongitude(driverSignupRequest.getLongitude());

        Driver createdDriver = driverRepository.save(driver);

        savedLicense.setDriver(createdDriver);
        savedVehicle.setDriver(createdDriver);

        licenseRepository.save(savedLicense);
        vehicleRepository.save(savedVehicle);

        return createdDriver;
    }

    @Override
    public List<Driver> getAvailableDrivers(double pickupLatitude, double pickupLongitude, Ride ride) {

        List<Driver> allDrivers = driverRepository.findAll();

        List<Driver> availableDriver = new ArrayList<>();

        for (Driver driver : allDrivers) {
            if (driver.getCurrentRide() != null && driver.getCurrentRide().getRideStatus() != RideStatus.COMPLETED) {
                continue;
            }
            if (ride.getDeclinedDrivers().contains(driver.getId())) {

                System.out.println("It contains");
                continue;
            }
            double driverLatitude = driver.getLatitude();
            double driverLongitude = driver.getLongitude();

            double distance = distanceCalculator.calculateDistance(driverLatitude, driverLongitude, pickupLatitude, pickupLongitude);

            availableDriver.add(driver);
        }
        return availableDriver;
    }

    @Override
    public Driver findNearestDriver(List<Driver> availableDrivers, double pickupLatitude, double pickupLongitude) {

        double min = Double.MAX_VALUE;

        Driver nearestDriver = null;

        for (Driver driver : availableDrivers) {

            double driverLatitude = driver.getLatitude();
            double driverLongitude = driver.getLongitude();

            double distance = distanceCalculator.calculateDistance(pickupLatitude, pickupLongitude, driverLatitude, driverLongitude);

            if (min > distance) {
                min = distance;
                nearestDriver = driver;
            }
        }
        return nearestDriver;
    }

    @Override
    public Driver getReqDriverProfile(String jwt) throws DriverException {

        String email = jwtUtil.getEmailFromJwt(jwt);

        Driver driver = driverRepository.findByEmail(email);

        if (driver == null) {
            throw new DriverException("Driver doesn't exist with this email " + email);
        }
        return driver;
    }

    @Override
    public Ride getDriversCurrentRide(int driverId) throws DriverException {
        Driver driver = findDriverByDriverId(driverId);
        return driver.getCurrentRide();
    }

    @Override
    public List<Ride> getAllocatedRides(int driverId) throws RideException {

        return driverRepository.getAllocatedRides(driverId);
    }

    @Override
    public Driver findDriverByDriverId(int driverId) throws DriverException {

        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isPresent()) {
            return optionalDriver.get();
        }
        throw new DriverException("No driver found with this id " + driverId);
    }

    @Override
    public List<Ride> completedRides(int driverId) throws DriverException {

        return driverRepository.getCompletedRides(driverId);
    }
}
