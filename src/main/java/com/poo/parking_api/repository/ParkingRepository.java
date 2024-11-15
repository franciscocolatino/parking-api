package com.poo.parking_api.repository;

import com.poo.parking_api.domain.parking.Parking;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, String> {
    Optional<Parking> findById(String id);
}
