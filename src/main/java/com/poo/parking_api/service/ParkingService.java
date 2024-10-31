package com.poo.parking_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poo.parking_api.model.Parking;
import com.poo.parking_api.repository.ParkingRepository;

import java.util.List;

@Service
public class ParkingService {
    @Autowired
    private ParkingRepository parkingRepository;

    public List<Parking> getAllParking() {
        return parkingRepository.findAll();
    }

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Parking getParkingById(Long id) {
        return parkingRepository.findById(id).orElse(null);
    }

    public Parking saveParking(Parking parking) {
        return parkingRepository.save(parking);
    }

    public void deleteParking(Long id) {
        parkingRepository.deleteById(id);
    }
}