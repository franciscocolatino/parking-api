package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket")
    public String tickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "ticket/index";
    }

    @PostMapping
    public String createTicket(@ModelAttribute Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable String id) {
        return ticketService.getTicket(id);
    }

/*    @PutMapping("/{id}/status")
    public void setTicketStatus(@PathVariable String id, @RequestBody String status) {
        ticketService.setTicketStatus(id, status);
    }*/

    @PostMapping("/payment")
    public float calculatePayment(@RequestBody Ticket ticket) {
        return ticketService.calculatePayment(ticket);
    }
}
