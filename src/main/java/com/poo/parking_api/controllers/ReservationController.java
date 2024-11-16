package com.poo.parking_api.controllers;
import com.poo.parking_api.domain.reservation.Reservation;
import com.poo.parking_api.dto.ReservationDTO;
import com.poo.parking_api.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.createReservation(
                reservationDTO.getCustomerName(),
                reservationDTO.getPlateCar(),
                reservationDTO.getVacancyId(),
                reservationDTO.getEmployeeId()
        );
    }

    @PutMapping("/{id}/complete")
    public String completeReservation(@PathVariable String id) {
        return reservationService.completeReservation(id);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable String id) {
        return reservationService.getReservationById(id);
    }
}
