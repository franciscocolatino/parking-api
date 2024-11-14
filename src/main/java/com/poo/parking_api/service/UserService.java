package com.poo.parking_api.service;

import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.user.UserRepository;
import com.poo.parking_api.domain.user.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public Boolean update(String id, User user) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            // Salva o usuário atualizado no banco de dados
            repository.save(existingUser);
            return true;
        }
        return false;
    }

}
