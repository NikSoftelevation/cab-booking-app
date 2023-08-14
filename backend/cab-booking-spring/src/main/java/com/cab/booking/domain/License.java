package com.cab.booking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String licenseNumber;
    private String licenseState;
    private String licenseExpirationDate;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Driver driver;
}
