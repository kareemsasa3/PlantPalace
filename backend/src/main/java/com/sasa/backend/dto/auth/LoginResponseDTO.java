package com.sasa.backend.dto.auth;

import com.sasa.backend.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private UserDTO user;
}
