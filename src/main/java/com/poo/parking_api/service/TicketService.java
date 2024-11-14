package com.poo.parking_api.service;

import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket getTicket(int id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public void setTicketStatus(int id, String status) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket != null) {
            ticket.setStatus(status);
            ticketRepository.save(ticket);
        }
    }

    public float calculatePayment(Ticket ticket) {
        return ticket.getPaymentTotal();
    }
}
