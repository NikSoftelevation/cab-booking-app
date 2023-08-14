package com.cab.booking.repository;

import com.cab.booking.domain.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License, Integer> {
}
