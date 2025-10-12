package com.instacare.authservice.dto;

import lombok.Getter;

@Getter
public class LoginResDTO {
    public LoginResDTO(String token) {
        this.token = token;
    }

    private final String token;
}
