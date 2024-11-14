package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable int id) {
        return ticketService.getTicket(id);
    }

    @PutMapping("/{id}/status")
    public void setTicketStatus(@PathVariable int id, @RequestBody String status) {
        ticketService.setTicketStatus(id, status);
    }

    @PostMapping("/payment")
    public float calculatePayment(@RequestBody Ticket ticket) {
        return ticketService.calculatePayment(ticket);
    }
}
