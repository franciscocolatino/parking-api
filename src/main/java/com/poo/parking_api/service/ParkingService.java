package com.poo.parking_api.service;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService implements BaseService<Parking, String> {

    @Autowired
    private ParkingRepository parkingRepository;
    @Autowired
    private VacancyService vacancyService;

    @Override
    public Parking create(Parking parking) {
        parkingRepository.save(parking);
        vacancyService.createVacancies(parking);
        return parking;
    }

    @Override
    public Parking update(Parking parking) {
        if (parkingRepository.existsById(parking.getId())) {
            return parkingRepository.save(parking);
        }
        throw new IllegalArgumentException("Parking not found for update");
    }

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }
    @Override
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Parking not found"));
    }

    public void delete(String id) {
        parkingRepository.deleteById(id);
    }
}