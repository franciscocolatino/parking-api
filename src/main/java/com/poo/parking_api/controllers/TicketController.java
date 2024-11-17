package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.domain.ticket.TicketStatus;
import com.poo.parking_api.domain.vacancy.PriorityType;
import com.poo.parking_api.service.ParkingService;
import com.poo.parking_api.service.TicketService;
import com.poo.parking_api.service.UserService;
import com.poo.parking_api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
        model.addAttribute("statuses", TicketStatus.values());
        model.addAttribute("parkings", parkingService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("priorityTypes", PriorityType.values());
        return "ticket/new";
    }

    @PostMapping("/ticket")
    public String createTicket(@ModelAttribute Ticket ticket) {
        String message = ticketService.createTicket(ticket);
        return "redirect:/tickets?" + message;
    }

    @GetMapping("/ticket/delete/{id}")
    public String deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets?hasSuccess=true&message=" + "Ticket apagado com sucesso!";
    }

    @GetMapping("/ticket/{id}")
    public String getTicket(@PathVariable String id, Model model) {
        model.addAttribute("ticket", ticketService.getTicket(id));
        model.addAttribute("statuses", TicketStatus.values());
        return "ticket/show";
    }

    @PostMapping("/ticket/update/{id}")
    public String updateTicketStatus(@PathVariable String id, @RequestParam(value = "status", required = false) TicketStatus status,
                                     @RequestParam(value = "dateEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateEnd,
                                     Model model) {
        try {
            ticketService.updateTicket(id, status, dateEnd);
        } catch (IllegalArgumentException e) {
            return "redirect:/ticket/" + id + "?hasError=true&message=" + e.getMessage();
        }

        return "redirect:/ticket/" + id + "?hasSuccess=true&message=Ticket atualizado com sucesso!";
    }

    @PostMapping("/payment")
    public float calculatePayment(@RequestBody Ticket ticket) {
        return ticketService.calculatePayment(ticket);
    }

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalStateException(IllegalStateException e) {
        return "redirect:/tickets?hasError=true&message=" + e.getMessage();
    }
}
