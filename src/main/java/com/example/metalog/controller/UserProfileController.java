package com.example.metalog.controller;

import com.example.metalog.config.CustomUserDetails;
import com.example.metalog.dto.UserProfileRequestDTO;
import com.example.metalog.dto.UserProfileResponseDTO;
import com.example.metalog.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponseDTO> updateUserProfile(@PathVariable Long id,
                                                                    @ModelAttribute UserProfileRequestDTO userProfileRequestDTO,
                                                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        UserProfileResponseDTO responseDTO = userProfileService.updateUserProfile(id, userProfileRequestDTO, userId);
        if (responseDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{id}/update-picture")
    public ResponseEntity<UserProfileResponseDTO> updateProfilePicture(@PathVariable Long id,
                                                                       @RequestParam("profilePicture") MultipartFile file,
                                                                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        UserProfileResponseDTO responseDTO = userProfileService.updateProfilePicture(id, file, userId);
        if (responseDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(@PathVariable Long id) {
        UserProfileResponseDTO responseDTO = userProfileService.getUserProfile(id);
        if (responseDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/profile-picture")
    public ResponseEntity<byte[]> getUserProfilePicture(@PathVariable Long id) {
        byte[] profilePicture = userProfileService.getUserProfilePicture(id);
        if (profilePicture != null) {
            return ResponseEntity.ok(profilePicture);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
