package com.cab.booking.repository;

import com.cab.booking.domain.Driver;
import com.cab.booking.domain.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    public Driver findByEmail(String email);

    @Query("Select R From Ride R Where R.rideStatus=REQUESTED AND R.driver.id=:driverId")
    public List<Ride> getAllocatedRides(@Param("driverId") int driverId);

    @Query("Select R From Ride R Where R.rideStatus=COMPLETED AND R.driver.id=:driverId")
    public List<Ride> getCompletedRides(@Param("driverId") int driverId);


}