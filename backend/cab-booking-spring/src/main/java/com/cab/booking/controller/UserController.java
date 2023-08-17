package com.cab.booking.controller;

import com.cab.booking.domain.Ride;
import com.cab.booking.domain.User;
import com.cab.booking.exceeption.UserException;
import com.cab.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserByIdHandler(@PathVariable("userId") int userId) throws UserException {

        System.out.println("Find by user Id");

        User createdUser = userService.findUserById(userId);

        return new ResponseEntity<>(createdUser, HttpStatus.ACCEPTED);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getReqUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {

        User reqUserProfile = userService.getReqUserProfile(token);

        return new ResponseEntity<>(reqUserProfile, HttpStatus.ACCEPTED);
    }

    @GetMapping("/rides/completed")
    public ResponseEntity<List<Ride>> getCompletedRides(@RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.getReqUserProfile(jwt);

        List<Ride> completedRides = userService.completedRides(user.getId());

        return new ResponseEntity<>(completedRides, HttpStatus.ACCEPTED);
    }
}
