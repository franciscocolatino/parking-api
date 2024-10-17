package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register_page";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        repository.save(user);
        return "redirect:/hello?success";
    }


    @GetMapping("/login")
    public String showLogin() {
        return "login_page";
    }


}
