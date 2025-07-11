package com.taskhub.taskhub_backend.controllers;

import com.taskhub.taskhub_backend.models.User;
import com.taskhub.taskhub_backend.repositories.UserRepository;
import com.taskhub.taskhub_backend.security.JwtUtil;
import com.taskhub.taskhub_backend.services.MailService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final MailService mailService;

    /**
     * Enregistre un nouvel utilisateur et envoie un email de confirmation.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email is already taken"));
        }

        String rawPassword = request.getPassword();
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(rawPassword))
                .fullName(request.getFullName())
                .role("EMPLOYER")
                .enabled(true)
                .build();

        user = userRepository.save(user);

        mailService.sendRegistrationEmail(user, rawPassword);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }


    /**
     * Authentifie un utilisateur et génère un token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            String token = jwtUtil.generateToken(request.getEmail());

            return ResponseEntity.ok(Map.of("token", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @Getter
    @Setter
    public static class RegisterRequest {
        private String email;
        private String password;
        private String fullName;
    }

    @Getter
    @Setter
    public static class LoginRequest {
        private String email;
        private String password;
    }
}

