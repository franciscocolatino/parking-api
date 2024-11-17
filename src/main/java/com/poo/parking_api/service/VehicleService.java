package com.poo.parking_api.service;

import com.poo.parking_api.domain.vehicle.Vehicle;
import com.poo.parking_api.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findById(String id) {
        return vehicleRepository.findById(id).orElse(null);
    }
}
