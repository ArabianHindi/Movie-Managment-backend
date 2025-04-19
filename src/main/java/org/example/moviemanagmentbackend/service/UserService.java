package org.example.moviemanagmentbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.moviemanagmentbackend.entity.User;
import org.example.moviemanagmentbackend.repository.UserRepository;
import org.example.moviemanagmentbackend.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}