package com.example.metalog.dto;

import com.example.metalog.entity.Role;
import com.example.metalog.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private Role role;
    private String email;
    private String password;
    private String username;

    public User toEntity() {
        return User.builder()
                .role(this.role)
                .email(this.email)
                .password(this.password)
                .username(this.username)
                .build();
    }
}
