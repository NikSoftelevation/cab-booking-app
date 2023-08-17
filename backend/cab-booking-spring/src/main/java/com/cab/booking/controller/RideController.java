package com.cab.booking.controller;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.Ride;
import com.cab.booking.domain.User;
import com.cab.booking.dto.DtoMapper;
import com.cab.booking.dto.RideDto;
import com.cab.booking.exceeption.DriverException;
import com.cab.booking.exceeption.RideException;
import com.cab.booking.exceeption.UserException;
import com.cab.booking.request.RideRequest;
import com.cab.booking.request.StartRideRequest;
import com.cab.booking.response.MessageResponse;
import com.cab.booking.service.DriverService;
import com.cab.booking.service.RideService;
import com.cab.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private UserService userService;

    @PostMapping("/request")
    public ResponseEntity<RideDto> userRequestRideHandler(@RequestBody RideRequest rideRequest, @RequestHeader("Authorization") String jwt) throws DriverException {

        User user = userService.getReqUserProfile(jwt);

        Ride ride = rideService.requestRide(rideRequest, user);

        RideDto rideDto = DtoMapper.toRideDto(ride);
        return new ResponseEntity<>(rideDto, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{rideId}/accept")
    public ResponseEntity<MessageResponse> acceptRideHandler(@PathVariable("rideId") int rideId) throws UserException, RideException {

        rideService.acceptRide(rideId);

        MessageResponse messageResponse = new MessageResponse("Ride accepted by driver");

        return new ResponseEntity<>(messageResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{rideId}/decline")
    public ResponseEntity<MessageResponse> declineRideHandler(@RequestHeader("Authorization") String jwt, @PathVariable("rideId") int rideId) throws UserException, RideException, DriverException {

        Driver driver = driverService.getReqDriverProfile(jwt);

        rideService.declineRide(rideId, driver.getId());

        MessageResponse messageResponse = new MessageResponse("Ride declined by driver");

        return new ResponseEntity<>(messageResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{rideId}/start")
    public ResponseEntity<MessageResponse> startRideHandler(@PathVariable("rideId") int rideId, StartRideRequest startRideRequest) throws UserException, RideException {

        rideService.startRide(rideId, startRideRequest.getOtp());

        MessageResponse messageResponse = new MessageResponse("Ride Started");

        return new ResponseEntity<>(messageResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{rideId}/complete")
    public ResponseEntity<MessageResponse> completeRideHandler(@PathVariable("rideId") int rideId) throws UserException, RideException {

        rideService.completeRide(rideId);

        MessageResponse messageResponse = new MessageResponse("Ride Completed! Thank-you for booking cab");
        return new ResponseEntity<>(messageResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<RideDto> findRideByIdHandler(@PathVariable("rideId") int rideId, @RequestHeader("Authorization") String token) throws UserException, RideException {

        User user = userService.getReqUserProfile(token);

        Ride ride = rideService.findRideById(rideId);

        RideDto rideDto = DtoMapper.toRideDto(ride);

        return new ResponseEntity<>(rideDto, HttpStatus.ACCEPTED);
    }
}
