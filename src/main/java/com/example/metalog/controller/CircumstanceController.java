package com.example.metalog.controller;

import com.example.metalog.dto.CircumstanceRequestDTO;
import com.example.metalog.dto.CircumstanceResponseDTO;
import com.example.metalog.service.CircumstanceService;
import com.example.metalog.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/circumstances")
@RequiredArgsConstructor
public class CircumstanceController {

    private final CircumstanceService service;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<CircumstanceResponseDTO> createCircumstance(@RequestHeader("Authorization") String accessToken,
                                                                      @RequestBody CircumstanceRequestDTO requestDTO) {
        Long userId = jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
        requestDTO.setUserId(userId);
        CircumstanceResponseDTO responseDTO = service.saveCircumstance(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CircumstanceResponseDTO> getCircumstance(@PathVariable Long id) {
        CircumstanceResponseDTO responseDTO = service.getCircumstance(id);
        if (responseDTO != null) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CircumstanceResponseDTO>> getAllCircumstances() {
        List<CircumstanceResponseDTO> circumstances = service.getAllCircumstances();
        return new ResponseEntity<>(circumstances, HttpStatus.OK);
    }
}
