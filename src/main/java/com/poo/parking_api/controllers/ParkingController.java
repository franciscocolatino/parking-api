package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping("/list")
    public String listParkings(Model model) {
        List<Parking> parkings = parkingService.findAll();
        model.addAttribute("parkings", parkings);
        return "parking_list";
    }

    @GetMapping("/new")
    public String createParkingForm(Model model) {
        model.addAttribute("parking", new Parking());
        return "new_parking";
    }

    @PostMapping("/save")
    public String saveParking(@ModelAttribute Parking parking) {
        parkingService.save(parking);
        return "redirect:/parking/list";
    }

    @GetMapping("/edit/{id}")
    public String editParkingForm(@PathVariable Long id, Model model) {
        Parking parking = parkingService.getParkingById(id);
        model.addAttribute("parking", parking);
        return "edit_parking";
    }

    @PostMapping("/update/{id}")
    public String updateParking(@PathVariable Long id, @ModelAttribute Parking parking) {
        parking.setId(id);
        parkingService.update(parking);
        return "redirect:/parking/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteParking(@PathVariable Long id) {
        parkingService.deleteParking(id);
        return "redirect:/parking/list";
    }
}
