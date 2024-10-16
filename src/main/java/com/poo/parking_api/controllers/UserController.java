package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.user.RequestUser;
import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;
    @GetMapping
    public ResponseEntity index() {
        var users = repository.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody RequestUser data) {
        User user = new User(data);
        repository.save(user);
        System.out.println(data);
        return ResponseEntity.ok(user);
    }
}
