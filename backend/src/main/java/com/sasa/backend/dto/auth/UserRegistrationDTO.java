package com.sasa.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDTO {
    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    @Size(min = 8, max = 50)
    @Pattern(
        regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$",
        message = "Password must be 8-50 characters long, contain at least one digit, one uppercase letter, one lowercase letter, one special character, and have no whitespace."
    )
    private String password;

    @NotBlank
    @Size(min = 8, max = 50)
    @Pattern(
        regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,50}$",
        message = "Password must be 8-50 characters long, contain at least one digit, one uppercase letter, one lowercase letter, one special character, and have no whitespace."
    )
    private String confirmPassword;

    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

    @Pattern(
        regexp = "^(ADMIN|USER|MODERATOR)$",
        message = "Invalid role name. Allowed values: ADMIN, USER, MODERATOR"
    )
    private String roleName;
}