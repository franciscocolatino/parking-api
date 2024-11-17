package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.user.UserRepository;
import com.poo.parking_api.domain.user.UserRole;
import com.poo.parking_api.repository.ParkingRepository;
import com.poo.parking_api.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private ParkingRepository parkingRepository;

    @GetMapping("/users")
    public String index(Model model) {
        List<User> users = repository.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("users/{id}")
    public String show(@PathVariable String id, Model model) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("roles", UserRole.values());
            model.addAttribute("parkings", parkingRepository.findAll());
        } else {
            return "redirect:/users?error=userNotFound";
        }
        return "users/show";
    }

    @DeleteMapping("users/{id}")
    @Transactional
    public ResponseEntity<User> delete(@PathVariable String id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            repository.delete(user);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }

    }

    @GetMapping("/users/new")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", UserRole.values());
        return "users/new";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute User user) {
        userService.register(user);
        return "redirect:/users?newUserCreated=true";
    }

    @PostMapping("/users/update/{id}")
    @Transactional
    public String update(@PathVariable String id, @ModelAttribute User user) {
        if (userService.update(id, user)) {
            return "redirect:/users/" + id + "?newUserUpdated=true";
        }
        return "redirect:/users?error=userNotFound";
    }


}
