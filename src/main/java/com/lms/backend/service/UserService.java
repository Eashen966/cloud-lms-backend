package com.lms.backend.service;

import com.lms.backend.model.User;
import com.lms.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        // Check matric number uniqueness
        if (userRepository.findByMatricNo(user.getMatricNo()).isPresent()) {
            throw new RuntimeException("Matric number is already registered!");
        }
        // FIX: only check email uniqueness if email was actually provided
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new RuntimeException("Email address is already in use!");
            }
        }
        // Encrypt password before saving
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public User authenticate(String matricNo, String plainPassword) {
        User user = userRepository.findByMatricNo(matricNo)
                .orElseThrow(() -> new RuntimeException("User not found with matching ID"));

        if (!passwordEncoder.matches(plainPassword, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials provided");
        }
        return user;
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }


}