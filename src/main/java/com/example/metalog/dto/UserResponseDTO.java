package com.example.metalog.dto;

import com.example.metalog.entity.User;
import lombok.*;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String role;
    private String email;
    private String username;

    public UserResponseDTO(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.username = entity.getUsername();
        // Enum -> String
        this.role = entity.getRole().name();
    }
}
