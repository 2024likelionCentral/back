package com.example.metalog.service;

import com.example.metalog.dto.UserProfileRequestDTO;
import com.example.metalog.dto.UserProfileResponseDTO;
import com.example.metalog.entity.UserProfile;
import com.example.metalog.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Value("${spring.file.upload-dir}")
    private String uploadDir; // 파일 업로드 디렉토리

    public UserProfileResponseDTO updateUserProfile(Long id, UserProfileRequestDTO requestDTO) throws IOException {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(id);
        if (optionalUserProfile.isEmpty()) {
            return null;
        }

        UserProfile userProfile = optionalUserProfile.get();
        userProfile.setUsername(requestDTO.getUsername());
        userProfile.setMotto(requestDTO.getMotto());

        if (requestDTO.getProfilePicture() != null && !requestDTO.getProfilePicture().isEmpty()) {
            // 프로필 사진 저장
            MultipartFile file = requestDTO.getProfilePicture();
            String fileName = file.getOriginalFilename();
            File fileToSave = new File(uploadDir + File.separator + fileName);
            try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                fos.write(file.getBytes());
            }
            userProfile.setProfilePicture(file.getBytes());
        }

        UserProfile updatedUserProfile = userProfileRepository.save(userProfile);
        return UserProfileResponseDTO.builder()
                .id(updatedUserProfile.getId())
                .username(updatedUserProfile.getUsername())
                .profilePictureUrl("/profile-pictures/" + updatedUserProfile.getId()) // 프로필 사진 URL
                .motto(updatedUserProfile.getMotto())
                .build();
    }

    public UserProfileResponseDTO getUserProfile(Long id) {
        return userProfileRepository.findById(id)
                .map(userProfile -> UserProfileResponseDTO.builder()
                        .id(userProfile.getId())
                        .username(userProfile.getUsername())
                        .profilePictureUrl("/profile-pictures/" + userProfile.getId()) // 프로필 사진 URL
                        .motto(userProfile.getMotto())
                        .build())
                .orElse(null);
    }
}
