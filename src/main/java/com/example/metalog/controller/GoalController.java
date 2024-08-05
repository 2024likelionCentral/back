package com.example.metalog.controller;

import com.example.metalog.config.CustomUserDetails;
import com.example.metalog.dto.GoalListResponseDTO;
import com.example.metalog.dto.GoalRequestDTO;
import com.example.metalog.dto.GoalResponseDTO;
import com.example.metalog.service.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public ResponseEntity<GoalResponseDTO> createGoal(@RequestBody GoalRequestDTO goalRequestDto, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        GoalResponseDTO responseDto = goalService.saveGoal(goalRequestDto, userId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<GoalResponseDTO> getGoal(
            @PathVariable Long goalId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        GoalResponseDTO responseDTO = goalService.getGoal(goalId, userId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<GoalListResponseDTO> getAllGoals(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        GoalListResponseDTO responseDTO = goalService.getAllGoals(userId);
        return ResponseEntity.ok(responseDTO);
    }


    @PatchMapping("/{goalId}/priority")
    public ResponseEntity<GoalResponseDTO> updatePriority(
            @PathVariable Long goalId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        GoalResponseDTO responseDTO = goalService.updatePriority(goalId, userId);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteGoal(
            @PathVariable Long goalId) {
        goalService.deleteGoal(goalId);
        return ResponseEntity.noContent().build();
    }

}
