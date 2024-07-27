package com.example.metalog.controller;

import com.example.metalog.dto.UserProfileRequestDTO;
import com.example.metalog.dto.UserProfileResponseDTO;
import com.example.metalog.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponseDTO> updateUserProfile(
            @PathVariable Long id,
            @ModelAttribute UserProfileRequestDTO requestDTO) {
        try {
            UserProfileResponseDTO responseDTO = userProfileService.updateUserProfile(id, requestDTO);
            if (responseDTO != null) {
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(@PathVariable Long id) {
        UserProfileResponseDTO responseDTO = userProfileService.getUserProfile(id);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
