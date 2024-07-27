package com.example.metalog.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponseDTO {
    private Long id;
    private String username;
    private String profilePictureUrl; // 프로필 사진 URL
    private String motto;
}
