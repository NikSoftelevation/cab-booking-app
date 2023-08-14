package com.cab.booking.response;

import com.cab.booking.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {

    private String jwt;
    private String message;
    private boolean isAuthenticated;
    private boolean isError;
    private String errorDetails;
    private UserRole type;
}