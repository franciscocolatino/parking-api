package com.poo.parking_api.domain.parking;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.PrePersist;

public class ParkingEntityListener {

    @PrePersist
    public void beforeCreate(Parking parking) {
        System.out.println(parking.getTotalCapacity());
        parking.setVacanciesAvailable(parking.getTotalCapacity());
    }
}
