package com.example.metalog.controller;

import com.example.metalog.config.CustomUserDetails;
import com.example.metalog.dto.CircumstanceRequestDTO;
import com.example.metalog.dto.CircumstanceResponseDTO;
import com.example.metalog.dto.GoalResponseDTO;
import com.example.metalog.service.CircumstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/circumstances")
@RequiredArgsConstructor
public class CircumstanceController {

    private final CircumstanceService service;

    @PostMapping
    public ResponseEntity<CircumstanceResponseDTO> createCircumstance(
            @RequestBody CircumstanceRequestDTO requestDTO,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId(); // 인증된 사용자의 ID를 가져옴
        CircumstanceResponseDTO responseDTO = service.saveCircumstance(requestDTO, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CircumstanceResponseDTO> getCircumstance(@PathVariable Long id,@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        CircumstanceResponseDTO responseDTO = service.getCircumstance(id,userId);
        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping
    public ResponseEntity<List<CircumstanceResponseDTO>> getAllCircumstances(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        List<CircumstanceResponseDTO> circumstances = service.getAllCircumstances(userId);
        return new ResponseEntity<>(circumstances, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(
            @PathVariable Long id) {
        service.deleteCircumstance(id);
        return ResponseEntity.noContent().build();
    }


}
