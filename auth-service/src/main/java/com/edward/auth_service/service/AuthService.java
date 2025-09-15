package com.edward.auth_service.service;

import com.edward.auth_service.dto.SigninRequest;
import com.edward.auth_service.dto.SignupRequest;
import com.edward.auth_service.entity.User;
import com.edward.auth_service.exceptions.UserAlreadyExistsException;
import com.edward.auth_service.exceptions.UserNotFoundException;
import com.edward.auth_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(SignupRequest request) {
        if (userRepository.findByUsernameOrEmail(request.username(), request.email()).isPresent()) {
            throw new UserAlreadyExistsException("Username or email already exists");
        }
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        return userRepository.save(user);
    }

    public User signin(SigninRequest request) {
        User user = userRepository.findByUsernameOrEmail(request.username(), request.email())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UserNotFoundException("Invalid password");
        }

        return user;
    }
}
