package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // Register new user
    public String registerUser(User user) {
        // Check if username already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists!";
        }
        
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already registered!";
        }
        
        // Save user
        userRepository.save(user);
        return "Registration successful!";
    }
    
    // Login user
    public boolean loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        return user.isPresent();
    }
    
    // Find user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
