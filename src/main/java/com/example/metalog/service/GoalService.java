package com.example.metalog.service;

import com.example.metalog.dto.GoalRequestDTO;

import com.example.metalog.dto.GoalResponseDTO;
import com.example.metalog.entity.Goal;
import com.example.metalog.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    @Transactional
    public GoalResponseDTO saveGoal(GoalRequestDTO goalRequestDto) {
        Goal goal = Goal.builder()
                .goal(goalRequestDto.getGoal())
                .actions(goalRequestDto.getActions())
                .build();

        Goal savedGoal = goalRepository.save(goal);

        return GoalResponseDTO.builder()
                .id(savedGoal.getId())
                .goal(savedGoal.getGoal())
                .actions(savedGoal.getActions())
                .createdTime(savedGoal.getCreatedTime())
                .build();
    }

    @Transactional(readOnly = true)
    public GoalResponseDTO getGoal(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        return GoalResponseDTO.builder()
                .id(goal.getId())
                .goal(goal.getGoal())
                .actions(goal.getActions())
                .createdTime(goal.getCreatedTime())
                .build();
    }
}
