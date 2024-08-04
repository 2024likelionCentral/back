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

    public UserProfileResponseDTO updateUserProfile(Long id, UserProfileRequestDTO userProfileRequestDTO) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(id);

        if (userProfileOptional.isPresent()) {
            UserProfile userProfile = userProfileOptional.get();
            userProfile.setUsername(userProfileRequestDTO.getUsername());
            userProfile.setMotto(userProfileRequestDTO.getMotto());

            MultipartFile profilePicture = userProfileRequestDTO.getProfilePicture();
            if (profilePicture != null && !profilePicture.isEmpty()) {
                try {
                    userProfile.setProfilePicture(profilePicture.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            UserProfile updatedUserProfile = userProfileRepository.save(userProfile);

            return UserProfileResponseDTO.builder()
                    .id(updatedUserProfile.getId())
                    .username(updatedUserProfile.getUsername())
                    .motto(updatedUserProfile.getMotto())
                    .profilePictureUrl("/user-profile/" + updatedUserProfile.getId() + "/profile-picture")
                    .build();
        } else {
            return null;
        }
    }

    public UserProfileResponseDTO getUserProfile(Long id) {
        return userProfileRepository.findById(id)
                .map(userProfile -> UserProfileResponseDTO.builder()
                        .id(userProfile.getId())
                        .username(userProfile.getUsername())
                        .motto(userProfile.getMotto())
                        .profilePictureUrl("/user-profile/" + userProfile.getId() + "/profile-picture")
                        .build())
                .orElse(null);
    }

    public byte[] getUserProfilePicture(Long id) {
        return userProfileRepository.findById(id)
                .map(UserProfile::getProfilePicture)
                .orElse(null);
    }
}
