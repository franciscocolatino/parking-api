package com.poo.parking_api.repository;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.vacancy.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VacancyRepository extends JpaRepository<Vacancy, String> {
}
