package com.poo.parking_api.domain.ticket;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;

import java.util.UUID;

public class TicketEntityListener {

    @PrePersist
    public void generateCode(Ticket ticket) {
        ticket.setCode(UUID.randomUUID().toString());
    }

    @PreRemove
    public void validateBeforeRemove(Ticket ticket) {
        if (!ticket.getStatus().equals(TicketStatus.FINALIZADO)) {
            throw new IllegalStateException("errorCanNotRemoveTicketPending=true");
        }
    }
}
