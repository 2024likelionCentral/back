package com.example.metalog.service;

import com.example.metalog.dto.UserProfileRequestDTO;
import com.example.metalog.dto.UserProfileResponseDTO;
import com.example.metalog.entity.UserProfile;
import com.example.metalog.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileResponseDTO updateUserProfile(Long id, UserProfileRequestDTO requestDTO, Long userId) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(id);
        if (optionalUserProfile.isPresent() && optionalUserProfile.get().getId().equals(userId)) {
            UserProfile userProfile = optionalUserProfile.get();
            userProfile.setUsername(requestDTO.getUsername());
            userProfile.setMotto(requestDTO.getMotto());
            userProfileRepository.save(userProfile);

            return UserProfileResponseDTO.builder()
                    .id(userProfile.getId())
                    .username(userProfile.getUsername())
                    .motto(userProfile.getMotto())
                    .profilePicture(userProfile.getProfilePicture())
                    .build();
        }
        return null;
    }

    public UserProfileResponseDTO updateProfilePicture(Long id, MultipartFile file, Long userId) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(id);
        if (optionalUserProfile.isPresent() && optionalUserProfile.get().getId().equals(userId)) {
            UserProfile userProfile = optionalUserProfile.get();
            try {
                userProfile.setProfilePicture(file.getBytes());
                userProfileRepository.save(userProfile);

                return UserProfileResponseDTO.builder()
                        .id(userProfile.getId())
                        .username(userProfile.getUsername())
                        .motto(userProfile.getMotto())
                        .profilePicture(userProfile.getProfilePicture())
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error
            }
        }
        return null;
    }

    public UserProfileResponseDTO getUserProfile(Long id) {
        return userProfileRepository.findById(id)
                .map(userProfile -> UserProfileResponseDTO.builder()
                        .id(userProfile.getId())
                        .username(userProfile.getUsername())
                        .motto(userProfile.getMotto())
                        .profilePicture(userProfile.getProfilePicture())
                        .build())
                .orElse(null);
    }

    public byte[] getUserProfilePicture(Long id) {
        return userProfileRepository.findById(id)
                .map(UserProfile::getProfilePicture)
                .orElse(null);
    }
}
