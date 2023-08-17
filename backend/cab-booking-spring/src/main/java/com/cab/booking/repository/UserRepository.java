package com.cab.booking.repository;

import com.cab.booking.domain.Ride;
import com.cab.booking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);

    @Query("Select R from Ride R where R.rideStatus=COMPLETED AND R.user.id=:userId")
    public List<Ride> getCompletedRides(@Param("userId") int userId);
}
