package com.example.metalog.service;

import com.example.metalog.dto.UserProfileResponseDTO;
import com.example.metalog.entity.UserProfile;
import com.example.metalog.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public UserProfileResponseDTO updateUserProfile(Long id, String username, String motto, MultipartFile profilePicture) {
        UserProfile userProfile = userProfileRepository.findById(id).orElse(null);
        if (userProfile == null) {
            return null;
        }

        // 사용자 정보 업데이트
        if (username != null) userProfile.setUsername(username);
        if (motto != null) userProfile.setMotto(motto);

        // 프로필 사진 처리
        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                userProfile.setProfilePicture(profilePicture.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 업데이트된 사용자 프로필 저장
        UserProfile updatedProfile = userProfileRepository.save(userProfile);

        // DTO로 변환하여 반환
        return UserProfileResponseDTO.builder()
                .id(updatedProfile.getId())
                .username(updatedProfile.getUsername())
                .motto(updatedProfile.getMotto())
                .profilePicture(updatedProfile.getProfilePicture())
                .build();
    }

    public UserProfileResponseDTO getUserProfile(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id).orElse(null);
        if (userProfile != null) {
            return UserProfileResponseDTO.builder()
                    .id(userProfile.getId())
                    .username(userProfile.getUsername())
                    .motto(userProfile.getMotto())
                    .profilePicture(userProfile.getProfilePicture())
                    .build();
        } else {
            return null;
        }
    }

    public byte[] getUserProfilePicture(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id).orElse(null);
        if (userProfile != null) {
            return userProfile.getProfilePicture();
        }
        return null;
    }
}
