package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.user.UserRole;
import com.poo.parking_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", UserRole.values());
        return "register_page";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:/login?success=true";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login_page";
    }
}
