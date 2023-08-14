package com.cab.booking.domain;

import com.cab.booking.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String fullName;

    private String email;

    @Column(unique = true)
    private String mobile;

    private String password;
    private String profilePicture;
    private UserRole userRole;
}
