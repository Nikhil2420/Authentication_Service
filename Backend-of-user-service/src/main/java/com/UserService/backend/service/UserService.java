package com.UserService.backend.service;

import com.UserService.backend.entity.User;
import com.UserService.backend.repository.UserRepository;
import com.UserService.backend.security.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String registerUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());

        userRepository.save(user);
        return "User registered successfully";
    }

    public String loginUser(String userName, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password)
        );
        System.out.println("hey " + " " + auth.isAuthenticated());

        if (auth.isAuthenticated()) {
            return JwtUtil.generateToken(userName);
        }

        throw new RuntimeException("Invalid credentials");
    }
}
