package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping("/parking")
    public String create(@ModelAttribute Parking parking) {
        parkingService.create(parking);
        return "redirect:/parkings";
    }

    @GetMapping("/parkings")
    public String listParkings(Model model) {
        List<Parking> parkings = parkingService.findAll();
        model.addAttribute("parkings", parkings);
        return "parking/index";
    }

    @GetMapping("/parking/new")
    public String createParking(Model model) {
        model.addAttribute("parking", new Parking());
        return "parking/new";
    }

    @PostMapping("/parking/update/{id}")
    public String updateParking(@PathVariable String id, @ModelAttribute Parking parking) {
        parking.setId(id);
        parkingService.update(parking);
        return "redirect:/parkings";
    }
    @GetMapping("/parking/delete/{id}")
    public String deleteParking(@PathVariable String id) {
        parkingService.delete(id);
        return "redirect:/parkings";
    }

    @GetMapping("/parking/edit/{id}")
    public String showParking(@PathVariable String id, Model model) {
        Parking parking = parkingService.findById(id);
        model.addAttribute("parking", parking);
        model.addAttribute("vacancies", parking.getVacancies());
        return "parking/show";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalStateException(IllegalStateException e) {
        return "redirect:/parkings?" + e.getMessage();
    }
}
