package com.poo.parking_api.repository;

import com.poo.parking_api.domain.parking.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, String> {
}
