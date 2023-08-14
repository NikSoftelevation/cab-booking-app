package com.cab.booking.domain;

import com.cab.booking.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String mobile;
    private double rating;
    private double latitude;
    private double longitude;
    private UserRole userRole;
    private String password;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    private License license;


    @JsonIgnore
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ride> rides;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Vehicle vehicle;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Ride currentRide;
    private Integer totalRevenue = 0;
}
