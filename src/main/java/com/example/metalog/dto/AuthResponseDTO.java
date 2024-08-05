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
    @Getter
    @Setter
    private Long userId;

    @Builder
    public AuthResponseDTO(Auth auth, Long userId) {
        this.userId = userId;
        this.tokenType = auth.getTokenType();
        this.accessToken = auth.getAccessToken();
        this.refreshToken = auth.getRefreshToken();

    }

}
