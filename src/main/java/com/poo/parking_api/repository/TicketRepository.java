package com.poo.parking_api.repository;

import com.poo.parking_api.domain.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {
 /*   @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Ticket t " +
            "WHERE t.vacancy.id = :vacancyId " +
            "AND ((t.dateStart BETWEEN :dateStart AND :dateEnd) " +
            "OR (t.dateEnd BETWEEN :dateStart AND :dateEnd) " +
            "OR (:dateStart BETWEEN t.dateStart AND t.dateEnd) " +
            "OR (:dateEnd BETWEEN t.dateStart AND t.dateEnd))")
    boolean existsConflictingTicket(@Param("vacancyId") String vacancyId,
                                    @Param("dateStart") Date dateStart,
                                    @Param("dateEnd") Date dateEnd);*/
}
