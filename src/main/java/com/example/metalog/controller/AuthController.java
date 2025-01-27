package com.example.metalog.controller;
import com.example.metalog.dto.AuthRequestDTO;
import com.example.metalog.dto.AuthResponseDTO;
import com.example.metalog.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.metalog.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO requestDto) {
        AuthResponseDTO responseDto = this.authService.login(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> singUp(@RequestBody UserRequestDTO requestDto) {
        this.authService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully.");

    }


    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("REFRESH_TOKEN") String refreshToken) {
        String newAccessToken = this.authService.refreshToken(refreshToken);
        Map<String, String> response = new HashMap<>();
        response.put("accessToken", newAccessToken);
        return ResponseEntity.ok(response);
    }
}
