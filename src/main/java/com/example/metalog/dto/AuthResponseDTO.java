package com.example.metalog.dto;

import com.example.metalog.entity.Auth;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String tokenType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public AuthResponseDTO(Auth auth) {
        this.tokenType = auth.getTokenType();
        this.accessToken = auth.getAccessToken();
        this.refreshToken = auth.getRefreshToken();
    }
}
