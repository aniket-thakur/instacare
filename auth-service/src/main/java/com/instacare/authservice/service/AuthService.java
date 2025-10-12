package com.instacare.authservice.service;

import com.instacare.authservice.dto.LoginReqDTO;
import com.instacare.authservice.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> authenticate(LoginReqDTO loginReqDTO) {
        Optional<String> token = userService.findByEmail(loginReqDTO.getEmail())
                .filter(usr -> passwordEncoder.matches(loginReqDTO.getPassword(),
                        usr.getPassword()))
                .map(usr -> jwtUtil.generateToken(usr.getEmail(), usr.getRole()));

        return token;
    }

    public boolean validateToken(String authToken) {
        try {
            jwtUtil.validateToken(authToken);
            return true;
        } catch (JwtException e) {
            return false;
        }

    }
}
