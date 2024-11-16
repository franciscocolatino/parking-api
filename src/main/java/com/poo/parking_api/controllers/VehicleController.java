package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.parking.Parking;
import com.poo.parking_api.domain.vehicle.Vehicle;
import com.poo.parking_api.domain.vehicle.VehicleType;
import com.poo.parking_api.service.ParkingService;
import com.poo.parking_api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicle/new")
    public String newVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("vehicleTypes", VehicleType.values());
        return "vehicle/new";
    }

    @PostMapping("/vehicle")
    public String create(@ModelAttribute Vehicle vehicle) {
        vehicleService.create(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/vehicles")
    public String vehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        return "vehicle/index";
    }

}
