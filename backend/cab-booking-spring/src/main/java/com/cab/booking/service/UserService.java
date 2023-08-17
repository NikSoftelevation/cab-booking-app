package com.cab.booking.service;

import com.cab.booking.domain.Ride;
import com.cab.booking.domain.User;
import com.cab.booking.exceeption.UserException;

import java.util.List;

public interface UserService {

    //    public User createUser(User user) throws UserException;
    public User getReqUserProfile(String token) throws UserException;

    public User findUserById(int userId) throws UserException;

    //    public User findUserByEmail(String email) throws UserException;
    public List<Ride> completedRides(int userId) throws UserException;
}
