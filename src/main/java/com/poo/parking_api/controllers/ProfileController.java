package com.poo.parking_api.controllers;


import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.user.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        model.addAttribute("user", user);
        model.addAttribute("role", userDetails.getAuthorities().stream().findFirst().get().getAuthority());

        return "profile/index";
    }
}
