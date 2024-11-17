package com.poo.parking_api.domain.parking;

import com.poo.parking_api.domain.vacancy.PriorityType;
import com.poo.parking_api.domain.vacancy.Vacancy;
import com.poo.parking_api.domain.vacancy.VacancyType;
import com.poo.parking_api.repository.ParkingRepository;
import com.poo.parking_api.repository.VacancyRepository;
import com.poo.parking_api.service.VacancyService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;

public class ParkingEntityListener {

    @PrePersist
    public void beforeCreate(Parking parking) {
        int total = parking.getMotocycleCapacity();
        total += parking.getTruckCapacity();
        total += parking.getCarCapacity();
        total += parking.getBicycleCapacity();
        parking.setTotalCapacity(total);
        parking.setVacanciesAvailable(total);
    }
}
