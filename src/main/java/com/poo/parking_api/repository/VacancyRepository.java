package com.poo.parking_api.repository;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.vacancy.PriorityType;
import com.poo.parking_api.domain.vacancy.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, String> {
    @Query("""
    SELECT v 
    FROM vacancy v
    WHERE v.parking = :parking
      AND v.priorityType = :priorityType
      AND NOT EXISTS (
          SELECT t 
          FROM ticket t
          WHERE t.vacancy = v
            AND (t.dateStart <= :dateEnd AND t.dateEnd >= :dateStart)
      )
    """)
    List<Vacancy> findAvailableVacancyByParking(
            @Param("parking") Parking parking,
            @Param("dateStart") LocalDateTime dateStart,
            @Param("dateEnd") LocalDateTime dateEnd,
            @Param("priorityType") PriorityType priorityType
    );



}
