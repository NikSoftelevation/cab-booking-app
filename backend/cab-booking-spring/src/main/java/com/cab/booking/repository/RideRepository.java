package com.cab.booking.repository;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {
}
