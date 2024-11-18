package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.domain.ticket.TicketStatus;
import com.poo.parking_api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/pay/{ticketId}")
    public String showPaymentPage(@PathVariable String ticketId, Model model) {
        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket != null) {
            float paymentAmount = ticketService.calculatePayment(ticket);
            model.addAttribute("ticket", ticket);
            model.addAttribute("paymentAmount", paymentAmount);
            return "payment/payment-form";
        }
        model.addAttribute("errorMessage", "Ticket não encontrado.");
        return "error";
    }

    @PostMapping("/pay/{ticketId}")
    public String processPayment(@PathVariable String ticketId, @RequestParam float paidAmount, Model model) {
        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket != null) {
            float paymentAmount = ticketService.calculatePayment(ticket);
            if (paidAmount >= paymentAmount) {
                ticketService.updateTicket(ticketId, TicketStatus.FINALIZADO, ticket.getDateEnd());
                model.addAttribute("successMessage", "Pagamento realizado com sucesso!");
                return "payment/payment-success";
            } else {
                model.addAttribute("errorMessage", "O valor pago é insuficiente.");
                return "payment/payment-form";
            }
        }
        return "error";
    }
}
