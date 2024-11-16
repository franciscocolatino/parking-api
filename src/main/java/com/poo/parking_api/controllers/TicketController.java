package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.domain.ticket.TicketStatus;
import com.poo.parking_api.domain.vacancy.PriorityType;
import com.poo.parking_api.service.ParkingService;
import com.poo.parking_api.service.TicketService;
import com.poo.parking_api.service.UserService;
import com.poo.parking_api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/tickets")
    public String tickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "ticket/index";
    }

    @GetMapping("/ticket/new")
    public String newTicket(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("statuses", TicketStatus.values()); // Enum de status
        model.addAttribute("parkings", parkingService.findAll()); // Lista de estacionamentos
        model.addAttribute("users", userService.findAll()); // Lista de usuários (se necessário)
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("priorityTypes", PriorityType.values());
        return "ticket/new";
    }

    @PostMapping("/ticket")
    public String createTicket(@ModelAttribute Ticket ticket) {
        String message = ticketService.createTicket(ticket);
        return "tickets?" + message;
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
