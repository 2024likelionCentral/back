package com.example.metalog.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}
