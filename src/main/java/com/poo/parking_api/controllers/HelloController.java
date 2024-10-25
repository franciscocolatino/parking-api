package com.poo.parking_api.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        model.addAttribute("name", userDetails.getUsername());
        model.addAttribute("roles", userDetails.getAuthorities());
        return "hello";
    }
}