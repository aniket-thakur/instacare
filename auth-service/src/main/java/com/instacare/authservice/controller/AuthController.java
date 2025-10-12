package com.instacare.authservice.controller;

import com.instacare.authservice.dto.LoginReqDTO;
import com.instacare.authservice.dto.LoginResDTO;
import com.instacare.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Generated token on user logins")
    @PostMapping("/login")
    public ResponseEntity<LoginResDTO> login(@RequestBody @Valid LoginReqDTO loginReqDTO) {
        Optional<String> optionalToken = authService.authenticate(loginReqDTO);
        if (optionalToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = optionalToken.get();
        System.out.printf(token);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResDTO(token));
    }

    @Operation(summary = "Validates the token")
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // sending only the token : Bearer <token>
        return authService.validateToken(authHeader.substring(7)) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

