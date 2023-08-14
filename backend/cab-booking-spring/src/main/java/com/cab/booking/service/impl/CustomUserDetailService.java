package com.cab.booking.service.impl;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.User;
import com.cab.booking.repository.DriverRepository;
import com.cab.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();

        User user = userRepository.findByEmail(username);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }

        Driver driver = driverRepository.findByEmail(username);

        if (driver != null) {

            return new org.springframework.security.core.userdetails.User(driver.getEmail(), driver.getPassword(), authorities);
        }

        throw new UsernameNotFoundException("User not found with username -  " + username);
    }
}
