package com.example.metalog.service;

import com.example.metalog.dto.GoalRequestDTO;
import com.example.metalog.dto.GoalResponseDTO;
import com.example.metalog.entity.Goal;
import com.example.metalog.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    @Transactional
    public GoalResponseDTO saveGoal(GoalRequestDTO goalRequestDto, Long userId) {

        Goal goal = Goal.builder()
                .goal(goalRequestDto.getGoal())
                .actions(goalRequestDto.getActions())
                .priority(false) // 기본적으로 우선순위는 false로 설정
                .userId(userId)
                .build();

        Goal savedGoal = goalRepository.save(goal);

        return GoalResponseDTO.builder()
                .id(savedGoal.getId())
                .goal(savedGoal.getGoal())
                .actions(savedGoal.getActions())
                .createdTime(savedGoal.getCreatedTime())
                .priority(savedGoal.isPriority())
                .userId((savedGoal.getUserId()))// 우선순위 추가
                .build();
    }

    @Transactional(readOnly = true)
    public GoalResponseDTO getGoal(Long goalId, Long userId) {
        Goal goal = goalRepository.findByIdAndUserId(goalId, userId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        return GoalResponseDTO.builder()
                .id(goal.getId())
                .goal(goal.getGoal())
                .actions(goal.getActions())
                .createdTime(goal.getCreatedTime())
                .priority(goal.isPriority())
                .userId(goal.getUserId())
                .build();
    }


    @Transactional(readOnly = true)
    public List<GoalResponseDTO> getAllGoals(Long userId) {
        List<Goal> goals = goalRepository.findAllByUserId(userId);
        return goals.stream()
                .map(goal -> GoalResponseDTO.builder()
                        .id(goal.getId())
                        .goal(goal.getGoal())
                        .actions(goal.getActions())
                        .createdTime(goal.getCreatedTime())
                        .priority(goal.isPriority())
                        .userId(goal.getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public GoalResponseDTO updatePriority(Long goalId, Long userId) {
        Goal currentPriorityGoal = goalRepository.findByPriorityTrueAndUserId(userId);
        if (currentPriorityGoal != null) {
            currentPriorityGoal.setPriority(false);
            goalRepository.save(currentPriorityGoal);
        }


        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        goal.setPriority(true);
        Goal updatedGoal = goalRepository.save(goal);

        return GoalResponseDTO.builder()
                .id(updatedGoal.getId())
                .goal(updatedGoal.getGoal())
                .actions(updatedGoal.getActions())
                .createdTime(updatedGoal.getCreatedTime())
                .priority(updatedGoal.isPriority())
                .build();
    }
}
