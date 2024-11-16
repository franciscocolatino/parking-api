package com.poo.parking_api.service;

import com.poo.parking_api.domain.reservation.Reservation;
import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(String customerName, String plateCar, int vacancyId, int employeeId) {
        Reservation reservation = new Reservation(customerName, plateCar, vacancyId, employeeId);
        Ticket ticket = new Ticket(null, plateCar, vacancyId, employeeId);
        reservation.setTicket(ticket);
        return reservationRepository.save(reservation);
    }

    public String completeReservation(String reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            // Aqui poderia haver lógica de finalização
            return "Reserva concluída: ID " + reservationId;
        }
        return "Reserva não encontrada: ID " + reservationId;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(String reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada: ID " + reservationId));
    }
}
