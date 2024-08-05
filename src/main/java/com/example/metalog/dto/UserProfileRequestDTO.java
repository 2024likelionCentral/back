package com.example.metalog.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRequestDTO {
    private String username;
    private String motto;
    private MultipartFile profilePicture;
    private Long userId;
}
