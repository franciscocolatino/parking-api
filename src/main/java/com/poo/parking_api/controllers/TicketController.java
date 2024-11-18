package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.ticket.Ticket;
import com.poo.parking_api.domain.ticket.TicketStatus;
import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.vacancy.PriorityType;
import com.poo.parking_api.domain.vehicle.Vehicle;
import com.poo.parking_api.domain.vehicle.VehicleType;
import com.poo.parking_api.service.ParkingService;
import com.poo.parking_api.service.TicketService;
import com.poo.parking_api.service.UserService;
import com.poo.parking_api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/ticket/operator_new")
    public String newTicketOperator(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Vehicle> vehicles = vehicleService.findAll();
        PriorityType[] priorityTypes = PriorityType.values();

        VehicleType[] vehicleTypes = VehicleType.values();

        model.addAttribute("ticket", new Ticket());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("priorityTypes", priorityTypes);
        model.addAttribute("status", TicketStatus.ATIVO);
        model.addAttribute("vehicleTypes", vehicleTypes);

        return "ticket/operator_new";
    }

    @PostMapping("/ticket/operator_new")
    public String generateTicketOperator(@ModelAttribute Ticket ticket, @AuthenticationPrincipal UserDetails userDetails,
                                         @RequestParam("vehicleSelection") String vehicleSelection) {

        // Lógica para salvar o veículo (caso necessário)
        if ("new".equals(vehicleSelection)) {
            // O veículo será setado automaticamente no ticket devido ao uso do th:field no formulário
            Vehicle newVehicle = ticket.getVehicle();
            newVehicle = vehicleService.create(newVehicle);  // Salve o novo veículo
            ticket.setVehicle(newVehicle);
        } else if ("existing".equals(vehicleSelection)) {
            // Lógica para selecionar o veículo existente
            Vehicle existingVehicle = vehicleService.findById(ticket.getVehicle().getId()); // Acessando o id do veículo que foi passado no select
            ticket.setVehicle(existingVehicle);
        }
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Adicionar 5 horas à data e hora atual
        LocalDateTime dateStart = currentDateTime;
        LocalDateTime dateEnd = currentDateTime.plusHours(5);
        ticket.setDateStart(dateStart);
        ticket.setDateEnd(dateEnd);

        String name = userDetails.getUsername();
        User user = userService.findByName(name);

        ticket.setParking(user.getParking());
        ticket.setUser(user);
        String message = ticketService.createTicket(ticket);
        return "redirect:/tickets?" + message;
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

//    @GetMapping("/ticket/delete/{id}")
//    public String deleteTicket(@PathVariable String id) {
//        ticketService.deleteTicket(id);
//        return "redirect:/tickets?hasSuccess=true&message=" + "Ticket apagado com sucesso!";
//    }

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

//    @PostMapping("/payment")
//    public float calculatePayment(@RequestBody Ticket ticket) {
//        return ticketService.calculatePayment(ticket);
//    }

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalStateException(IllegalStateException e) {
        return "redirect:/tickets?hasError=true&message=" + e.getMessage();
    }
}
