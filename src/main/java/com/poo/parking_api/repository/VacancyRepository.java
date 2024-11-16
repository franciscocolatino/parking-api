package com.poo.parking_api.repository;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.vacancy.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, String> {
    @Query(value = "SELECT v FROM Vacancy v WHERE v.parking.id = :parkingId", nativeQuery = true)
    List<Vacancy> BuscarTestePorParking(@Param("parkingId") String parkingId);

}
