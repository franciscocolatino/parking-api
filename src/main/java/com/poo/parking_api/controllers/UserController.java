package com.poo.parking_api.controllers;

import com.poo.parking_api.domain.user.RequestUser;
import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
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
