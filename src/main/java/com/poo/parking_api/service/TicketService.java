package com.poo.parking_api.service;

import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.domain.vacancy.Vacancy;
import com.poo.parking_api.repository.TicketRepository;
import com.poo.parking_api.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    public String createTicket(Ticket ticket) {
        List<Vacancy> vacancies = vacancyRepository.findAvailableVacancyByParking(
                ticket.getParking(), ticket.getDateStart(),
                ticket.getDateEnd(), ticket.getPriorityType());
        Optional<Vacancy> firstVacancy = vacancies.stream().findFirst();
        if (firstVacancy.isEmpty()) return "parkingIsFull=true";

        Vacancy vacancy = firstVacancy.get();
        ticket.setVacancy(vacancy);
        ticketRepository.save(ticket);
        return "ticketCreated=true";
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
