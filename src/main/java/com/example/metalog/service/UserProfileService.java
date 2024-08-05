package com.example.metalog.service;

import com.example.metalog.dto.UserProfileRequestDTO;
import com.example.metalog.dto.UserProfileResponseDTO;
import com.example.metalog.entity.User;
import com.example.metalog.entity.UserProfile;
import com.example.metalog.repository.UserProfileRepository;
import com.example.metalog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @Transactional
    public UserProfileResponseDTO updateUserProfile(UserProfileRequestDTO requestDTO, String username) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUsername(username);
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUserProfile.isPresent() && optionalUser.isPresent()) {
            UserProfile userProfile = optionalUserProfile.get();
            User user = optionalUser.get();

            userProfile.setUsername(requestDTO.getUsername());
            userProfile.setMotto(requestDTO.getMotto());
            user.setUsername(requestDTO.getUsername());

            MultipartFile profilePicture = requestDTO.getProfilePicture();
            if (profilePicture != null && !profilePicture.isEmpty()) {
                try {
                    userProfile.setProfilePicture(profilePicture.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to save profile picture", e);
                }
            }

            userProfileRepository.save(userProfile);
            userRepository.save(user);

            return UserProfileResponseDTO.builder()
                    .id(userProfile.getId())
                    .username(userProfile.getUsername())
                    .motto(userProfile.getMotto())
                    .profilePicture(userProfile.getProfilePicture())
                    .build();
        }

        return null;
    }

    @Transactional
    public void createUserProfile(UserProfileRequestDTO requestDTO) {

        UserProfile userProfile = UserProfile.builder()
                .username(requestDTO.getUsername())
                .motto(requestDTO.getMotto())
                .profilePicture(null)
                .build();

        userProfileRepository.save(userProfile);
    }


    public UserProfileResponseDTO getUserProfile(String username) {
        return userProfileRepository.findByUsername(username)
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
