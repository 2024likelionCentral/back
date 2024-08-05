package com.example.metalog.controller;

import com.example.metalog.config.CustomUserDetails;
import com.example.metalog.dto.CountResponseDTO;
import com.example.metalog.service.CountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counts")
@RequiredArgsConstructor
public class CountController {

    private final CountService countService;

    @GetMapping
    public ResponseEntity<CountResponseDTO> getCounts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        CountResponseDTO countResponseDTO = countService.getCounts(userId);
        return ResponseEntity.ok(countResponseDTO);
    }
}
