package com.poo.parking_api.service;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;
    @Autowired
    private VacancyService vacancyService;

    public Parking create(Parking parking) {
        parkingRepository.save(parking);
        vacancyService.createVacancies(parking);
        return parking;
    }

    public Parking update(Parking parking) {
        if (parkingRepository.existsById(parking.getId())) {
            return parkingRepository.save(parking);
        }
        throw new IllegalArgumentException("Parking not found for update");
    }

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Parking getParkingById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Parking not found"));
    }

    public void deleteParking(String id) {
        parkingRepository.deleteById(id);
    }
}