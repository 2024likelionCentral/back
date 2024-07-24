package com.example.metalog.controller;

import com.example.metalog.config.JwtTokenProvider;
import com.example.metalog.dto.DiaryEntryResponseDTO;
import com.example.metalog.service.DiaryEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryEntryController {

    private final DiaryEntryService diaryEntryService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/entries")
    public ResponseEntity<List<DiaryEntryResponseDTO>> getDiaryEntriesByDate(@RequestHeader("Authorization") String accessToken,
                                                                             @RequestParam LocalDate date) {
        Long userId = jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
        List<DiaryEntryResponseDTO> diaryEntries = diaryEntryService.findEntriesByDate(userId, date);
        return ResponseEntity.ok(diaryEntries);
    }
}
