package com.example.metalog.service;

import com.example.metalog.dto.CountResponseDTO;
import com.example.metalog.repository.CircumstanceRepository;
import com.example.metalog.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountService {

    private final CircumstanceRepository circumstanceRepository;
    private final GoalRepository goalRepository;

    public CountResponseDTO getCounts(Long userId) {
        Long circumstancesCount = circumstanceRepository.countByUserId(userId);
        Long goalsCount = goalRepository.countByUserId(userId);
        return CountResponseDTO.builder()
                .circumstancesCount(circumstancesCount)
                .goalsCount(goalsCount)
                .build();
    }
}
