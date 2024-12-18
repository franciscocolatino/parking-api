package com.poo.parking_api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
     Optional<User> findByEmail(String email);
     Optional<User> findByName(String name);
}
