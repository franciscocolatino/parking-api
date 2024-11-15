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

    @GetMapping("/parking/list")
    public String listParkings(Model model) {
        List<Parking> parkings = parkingService.findAll();
        model.addAttribute("parkings", parkings);
        return "parking_list";
    }

    @GetMapping("/parking/new")
    public String createParkingForm(Model model) {
        model.addAttribute("parking", new Parking());
        return "new_parking";
    }

    @PostMapping("/parking/update/{id}")
    public String updateParking(@PathVariable String id, @ModelAttribute Parking parking) {
        parking.setId(id);
        parkingService.update(parking);
        return "redirect:/parking/list";
    }
    @GetMapping("/parking/delete/{id}")
    public String deleteParking(@PathVariable String id) {
        parkingService.deleteParking(id);
        return "redirect:/parking/list";
    }

    @GetMapping("/parking/edit/{id}")
    public String editParkingForm(@PathVariable String id, Model model) {
        Parking parking = parkingService.getParkingById(id);
        model.addAttribute("parking", parking);
        return "edit_parking";
    }
}
