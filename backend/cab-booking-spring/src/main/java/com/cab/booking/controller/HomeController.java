package com.cab.booking.controller;

import com.cab.booking.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<MessageResponse> homeController() {

        MessageResponse messageResponse = new MessageResponse("Welcome to OLA Backend System");

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
