package com.example.metalog.dto;

import com.example.metalog.entity.Role;
import com.example.metalog.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String email;
    private String username;
    private Role role;
    private String password;


    public User toEntity() {
        return User.builder()
                .role(this.role)
                .email(this.email)
                .password(this.password)
                .username(this.username)
                .build();
    }
}
