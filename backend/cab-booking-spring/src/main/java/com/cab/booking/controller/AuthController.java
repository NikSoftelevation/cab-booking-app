package com.cab.booking.controller;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.User;
import com.cab.booking.enums.UserRole;
import com.cab.booking.exceeption.UserException;
import com.cab.booking.jwt.JwtUtil;
import com.cab.booking.request.DriverSignupRequest;
import com.cab.booking.request.LoginRequest;
import com.cab.booking.response.JwtResponse;
import com.cab.booking.request.SignupRequest;
import com.cab.booking.repository.DriverRepository;
import com.cab.booking.repository.UserRepository;
import com.cab.booking.service.DriverService;
import com.cab.booking.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private DriverService driverService;

    @PostMapping("/user/signup")
    public ResponseEntity<JwtResponse> signUpHandler(@RequestBody SignupRequest signUpRequest) {

        String email = signUpRequest.getEmail();
        String fullName = signUpRequest.getFullName();
        String mobile = signUpRequest.getPassword();
        String password = signUpRequest.getMobile();


        User user = userRepository.findByEmail(email);

        if (user != null) {
            throw new UserException("User already exists with username " + email);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(password);

        User createdUser = new User();

        createdUser.setEmail(email);
        createdUser.setPassword(encodedPassword);
        createdUser.setFullName(fullName);
        createdUser.setUserRole(UserRole.USER);
        createdUser.setMobile(mobile);

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateJwtToken(authentication);

        JwtResponse jwtResponse = new JwtResponse();

        jwtResponse.setJwt(jwt);
        jwtResponse.setAuthenticated(true);
        jwtResponse.setError(false);
        jwtResponse.setErrorDetails(null);
        jwtResponse.setType(UserRole.USER);
        jwtResponse.setMessage("Account Created Successfully " + savedUser.getFullName());

        return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signInHandler(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getEmail();

        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateJwtToken(authentication);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwt(jwt);
        jwtResponse.setError(false);
        jwtResponse.setAuthenticated(true);
        jwtResponse.setErrorDetails(null);
        jwtResponse.setType(UserRole.USER);
        jwtResponse.setMessage("Account Logined Successfully");

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping("/driver/signup")
    public ResponseEntity<JwtResponse> driverSignUpHandler(@RequestBody DriverSignupRequest request) {

        Driver driver = driverRepository.findByEmail(request.getEmail());

        JwtResponse jwtResponse = new JwtResponse();

        if (driver != null) {

            jwtResponse.setAuthenticated(false);
            jwtResponse.setErrorDetails("Email already used with another account");
            jwtResponse.setError(true);

            return new ResponseEntity<>(jwtResponse, HttpStatus.BAD_REQUEST);
        }

        Driver createdDriver = driverService.registerDriver(request);

        Authentication authentication = new UsernamePasswordAuthenticationToken(createdDriver.getPassword(), createdDriver.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateJwtToken(authentication);

        jwtResponse.setJwt(jwt);
        jwtResponse.setAuthenticated(true);
        jwtResponse.setError(false);
        jwtResponse.setType(UserRole.DRIVER);
        jwtResponse.setMessage("Driver Account Created Successfully" + createdDriver.getName());

        return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);

    }

    private Authentication authenticate(String password, String username) {

        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password from authenticate method");
        }
        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {

            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


}
