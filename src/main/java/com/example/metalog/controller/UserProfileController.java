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

    @PutMapping("/update-profile")
    public ResponseEntity<UserProfileResponseDTO> updateUserProfile(
                                                                    @RequestBody UserProfileRequestDTO userProfileRequestDTO,
                                                                    @AuthenticationPrincipal CustomUserDetails userDetails) {

        String username = userDetails.getUsername();
        UserProfileResponseDTO responseDTO = userProfileService.updateUserProfile(userProfileRequestDTO, username);
        if (responseDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update-picture")
    public ResponseEntity<UserProfileResponseDTO> updateProfilePicture(
                                                                       @RequestParam("profilePicture") MultipartFile file,
                                                                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        String username = userDetails.getUsername();
        UserProfileResponseDTO responseDTO = userProfileService.updateProfilePicture(file, username);
        if (responseDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String username = userDetails.getUsername();
        UserProfileResponseDTO responseDTO = userProfileService.getUserProfile(username);
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
