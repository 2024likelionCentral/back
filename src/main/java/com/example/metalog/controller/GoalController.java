package com.example.metalog.controller;

import com.example.metalog.dto.GoalRequestDTO;
import com.example.metalog.dto.GoalResponseDTO;
import com.example.metalog.service.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public ResponseEntity<GoalResponseDTO> createGoal(@RequestBody GoalRequestDTO goalRequestDto) {
        GoalResponseDTO responseDto = goalService.saveGoal(goalRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<GoalResponseDTO> getGoal(@PathVariable Long goalId) {
        GoalResponseDTO responseDto = goalService.getGoal(goalId);
        return ResponseEntity.ok(responseDto);
    }
    @PutMapping("/{goalId}")
    public ResponseEntity<GoalResponseDTO> updateGoal(@PathVariable Long goalId, @RequestBody GoalRequestDTO goalRequestDto) {
        GoalResponseDTO response = goalService.updateGoal(goalId, goalRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long goalId) {
        goalService.deleteGoal(goalId);
        return ResponseEntity.noContent().build();
    }
}
