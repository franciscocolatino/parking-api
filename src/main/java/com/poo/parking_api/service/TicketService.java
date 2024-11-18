package com.poo.parking_api.service;

import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.domain.ticket.TicketStatus;
import com.poo.parking_api.domain.vacancy.Vacancy;
import com.poo.parking_api.repository.TicketRepository;
import com.poo.parking_api.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

//        if (firstVacancy.isEmpty()) return "hasError=true&message=" + "O Estacionamento já está cheio!";

        Vacancy vacancy = firstVacancy.get();
        ticket.setVacancy(vacancy);
        ticketRepository.save(ticket);
        return "hasSuccess=true&message=" + "Ticket criado com sucesso!";
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    public Ticket getTicket(String id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public void updateTicket(String id, TicketStatus status, LocalDateTime dateEnd) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Ticket não encontrado"));

        if (status != null) ticket.setStatus(status);
        if (dateEnd != null) ticket.setDateEnd(dateEnd);

        ticketRepository.save(ticket);
    }

    public void deleteTicket(String id) {
        ticketRepository.deleteById(id);
    }

    public float calculatePayment(Ticket ticket) {
        ticket.calcularValor();
        return (float) (float) ticket.getPaymentTotal();
    }

    public void finalizePayment(String id, float valorPago) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Ticket não encontrado"));

        ticket.finalizarPagamento(valorPago);
        ticketRepository.save(ticket);
    }
}
