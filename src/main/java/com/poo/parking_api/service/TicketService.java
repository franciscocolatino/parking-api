package com.poo.parking_api.service;

import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.repository.TicketRepository;
import com.poo.parking_api.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    public String createTicket(Ticket ticket) {
        System.out.println(vacancyRepository.buscarPorParking(ticket.getParking()));
        //boolean existsTicket =  ticketRepository.existsConflictingTicket(ticket.getVacancy().getId(), ticket.getDateStart(), ticket.getDateEnd());
        return "";
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    public Ticket getTicket(String id) {
        return ticketRepository.findById(id).orElse(null);
    }

/*    public void setTicketStatus(String id, String status) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket != null) {
            ticket.setStatus(status);
            ticketRepository.save(ticket);
        }
    }*/

    public float calculatePayment(Ticket ticket) {
        return ticket.getPaymentTotal();
    }
}
