package com.poo.parking_api.domain.ticket;

import com.poo.parking_api.domain.vacancy.VacancyType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class TicketEntityListener {

    @PrePersist
    public void prePersist(Ticket ticket) {
        generateCode(ticket);
        setPaymentTotal(ticket);
    }

    @PreUpdate
    public void preUpdate(Ticket ticket) {
        setPaymentTotal(ticket);
    }

    @PreRemove
    public void validateBeforeRemove(Ticket ticket) {
        TicketStatus status = ticket.getStatus();
        if (!status.equals(TicketStatus.ATIVO)) return;

        throw new IllegalStateException("Não é possível excluir um ticket ativo!");
    }

    private void generateCode(Ticket ticket) {
        ticket.setCode(UUID.randomUUID().toString());
    }

    private void setPaymentTotal(Ticket ticket) {
        if (ticket.getDateEnd() == null) return;

        float priceInHour = ticket.getVacancy().getVacancyType().getPriceInHour();

        long hours = Duration.between(ticket.getDateStart(), ticket.getDateEnd()).toHours();
        if (Duration.between(ticket.getDateStart(), ticket.getDateEnd()).toMinutes() % 60 > 0) {
            hours += 1;
        }
        ticket.setPaymentTotal(priceInHour * hours);
    }
}
