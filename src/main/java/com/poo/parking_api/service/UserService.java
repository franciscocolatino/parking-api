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

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements BaseService<User, String> {

    @Autowired
    private UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = repository.save(user);
        return user;
    }

    @Override
    public User update(User user) {
        User existing = repository.findById(user.getId()).get();
        existing.setEmail(user.getEmail());
        existing.setParking(user.getParking());
        existing.setRole(user.getRole());
        existing.setName(user.getName());
        existing = repository.save(existing);
        return existing;
    }

    @Override
    public User findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    public User findByName(String name) {
        Optional<User> userOptional = repository.findByName(name);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
        }
    }


}
