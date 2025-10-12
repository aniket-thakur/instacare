package com.instacare.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginReqDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Not a valid email")
    private String email;

    @NotBlank(message = "Password should not be empty")
    @Size(min = 8, message = "Password must be atleast 8 character long")
    private String password;
}
