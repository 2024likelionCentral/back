package com.example.metalog.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class UserProfileRequestDTO {
    private String username;
    private MultipartFile profilePicture; // 프로필 사진 업로드용
    private String motto;
}
