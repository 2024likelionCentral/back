package com.example.metalog.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponseDTO {
    private Long id;
    private String username;
    private String motto;
    private byte[] profilePicture;
}
