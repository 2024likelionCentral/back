package com.example.metalog.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Entity
public class Auth extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tokenType;

    @Column(nullable = false)
    private String accessToken;

    @Column(nullable = false)
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Auth(User user, String tokenType, String accessToken, String refreshToken) {
        this.user = user;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    // AccessToken 업데이트 메소드
    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    // RefreshToken 업데이트 메소드
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


}
