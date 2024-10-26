package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.user.UserRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    //@PreAuthorize("hasRole(UserRole.ADMIN)") COLOCA ISSO NA ROTA QUE QUISER BLOQUEAR
    public String hello(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        model.addAttribute("name", userDetails.getUsername());
        model.addAttribute("roles", userDetails.getAuthorities());

        return "hello";
    }
}