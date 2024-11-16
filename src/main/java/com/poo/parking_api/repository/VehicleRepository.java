package com.poo.parking_api.repository;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, String> { }
