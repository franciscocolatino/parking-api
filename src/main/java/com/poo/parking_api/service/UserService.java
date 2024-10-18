package com.poo.parking_api.service;

import com.poo.parking_api.domain.user.User;
import com.poo.parking_api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        System.out.println("@@@@@@@@@@@@");
        System.out.println(this.userRepository );
        System.out.println(this.passwordEncoder);
    }

    public void save(User user) {
        System.out.println("1111111111");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("2222222222222");

        userRepository.save(user);
    }
}
