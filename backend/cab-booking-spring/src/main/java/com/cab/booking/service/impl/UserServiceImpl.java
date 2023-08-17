package com.cab.booking.service.impl;

import com.cab.booking.domain.Ride;
import com.cab.booking.domain.User;
import com.cab.booking.exceeption.UserException;
import com.cab.booking.jwt.JwtUtil;
import com.cab.booking.repository.UserRepository;
import com.cab.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User getReqUserProfile(String token) throws UserException {

        String email = jwtUtil.getEmailFromJwt(token);

        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        }
        throw new UserException("invalid token");
    }

    @Override
    public User findUserById(int userId) throws UserException {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserException("User not found wih id " + userId);
    }

    @Override
    public List<Ride> completedRides(int userId) throws UserException {

        return userRepository.getCompletedRides(userId);
    }
}
