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
//목표 저장
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
//목표 가져오기
    @Transactional(readOnly = true)
    public GoalResponseDTO getGoal(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("해당 목표가 없음"));

        return GoalResponseDTO.builder()
                .id(goal.getId())
                .goal(goal.getGoal())
                .actions(goal.getActions())
                .createdTime(goal.getCreatedTime())
                .build();
    }

    //목표수정
    @Transactional
    public GoalResponseDTO updateGoal(Long goalId, GoalRequestDTO goalRequestDto) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("해당 목표가 없음"));

        goal.setGoal(goalRequestDto.getGoal());
        goal.setActions(goalRequestDto.getActions());

        Goal updatedGoal = goalRepository.save(goal);

        return GoalResponseDTO.builder()
                .id(updatedGoal.getId())
                .goal(updatedGoal.getGoal())
                .actions(updatedGoal.getActions())
                .createdTime(updatedGoal.getCreatedTime())
                .build();
    }

    //목표삭제
    @Transactional
    public void deleteGoal(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("해당 목표가 없음"));
        goalRepository.delete(goal);
    }

}
