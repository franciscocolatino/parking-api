package com.poo.parking_api.controllers;

import com.poo.parking_api.model.Parking;
import com.poo.parking_api.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/parking/list")
    public String listParkings(Model model) {
        List<Parking> parkings = parkingService.findAll();
        model.addAttribute("parkings");
        return "parking_list";
    }

    @GetMapping("/parking/new")
    public String createParkingForm(Model model) {
        model.addAttribute("parking", new Parking());
        return "new_parking";
    }

//    @PostMapping
//    public String saveParking(@ModelAttribute Parking parking) {
//        parkingService.save(parking);
//        return "redirect:/parkings";
//    }
//
//    @GetMapping("/{id}")
//    public String editParkingForm(@PathVariable Long id, Model model) {
//        Parking parking = parkingService.findById(id);
//        model.addAttribute("parking", parking);
//        return "edit_parking"; // Nome do seu template para editar
//    }
//
//    @PostMapping("/{id}")
//    public String updateParking(@PathVariable Long id, @ModelAttribute Parking parking) {
//        parking.setId(id);
//        parkingService.update(parking);
//        return "redirect:/parkings";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteParking(@PathVariable Long id) {
//        parkingService.delete(id);
//        return "redirect:/parkings";
//    }
}
