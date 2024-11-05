package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.user.RequestUser;
import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

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

    @PostMapping
    public ResponseEntity create(@RequestBody RequestUser data) {
        User user = new User(data);
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<User> update(@RequestBody RequestUser data) {
        Optional<User> optionalUser = repository.findById(data.id());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(data.name());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
