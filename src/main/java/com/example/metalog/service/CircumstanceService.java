package com.example.metalog.service;

import com.example.metalog.dto.CircumstanceRequestDTO;
import com.example.metalog.dto.CircumstanceResponseDTO;
import com.example.metalog.dto.GoalResponseDTO;
import com.example.metalog.entity.Circumstance;
import com.example.metalog.entity.Goal;
import com.example.metalog.repository.CircumstanceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CircumstanceService {

    private final CircumstanceRepository circumstanceRepository;

    public CircumstanceResponseDTO saveCircumstance(CircumstanceRequestDTO circumstanceRequestDTO,Long userId) {
        Circumstance circumstance = Circumstance.builder()
                .situation(circumstanceRequestDTO.getSituation())
                .emotions(circumstanceRequestDTO.getEmotions())
                .causes(circumstanceRequestDTO.getCauses())
                .conclusion(circumstanceRequestDTO.getConclusion())
                .userId(userId) // 사용자 ID 설정
                .build();

        Circumstance savedCircumstance = circumstanceRepository.save(circumstance);

        return CircumstanceResponseDTO.builder()
                .id(savedCircumstance.getId())
                .situation(savedCircumstance.getSituation())
                .emotions(savedCircumstance.getEmotions())
                .causes(savedCircumstance.getCauses())
                .conclusion(savedCircumstance.getConclusion())
                .userId(savedCircumstance.getUserId()) // 사용자 ID 반환
                .createdDate(savedCircumstance.getCreatedTime())
                .build();
    }

    public CircumstanceResponseDTO getCircumstance(Long id,Long userId) {

        Circumstance circumstance = circumstanceRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Circumstance not found"));

        return CircumstanceResponseDTO.builder()
                        .id(circumstance.getId())
                        .situation(circumstance.getSituation())
                        .emotions(circumstance.getEmotions())
                        .causes(circumstance.getCauses())
                        .conclusion(circumstance.getConclusion())
                        .userId(circumstance.getUserId()) // 사용자 ID 반환
                        .createdDate(circumstance.getCreatedTime())
                        .build();

    }

    public List<CircumstanceResponseDTO> getAllCircumstances(Long userId) {
        List<Circumstance> circumstances = circumstanceRepository.findAllByUserId(userId);
        return circumstances.stream()
                .map(circumstance -> CircumstanceResponseDTO.builder()
                        .id(circumstance.getId())
                        .situation(circumstance.getSituation())
                        .emotions(circumstance.getEmotions())
                        .causes(circumstance.getCauses())
                        .conclusion(circumstance.getConclusion())
                        .userId(circumstance.getUserId()) // 사용자 ID 반환
                        .createdDate(circumstance.getCreatedTime())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCircumstance(Long id) {
        Circumstance circumstance = circumstanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Circumstance not found"));

        circumstanceRepository.delete(circumstance);
    }
}
