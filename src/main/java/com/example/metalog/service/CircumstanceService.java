package com.example.metalog.service;

import com.example.metalog.dto.CircumstanceRequestDTO;
import com.example.metalog.dto.CircumstanceResponseDTO;
import com.example.metalog.entity.Circumstance;
import com.example.metalog.repository.CircumstanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CircumstanceService {

    private final CircumstanceRepository circumstanceRepository;

    public CircumstanceResponseDTO saveCircumstance(CircumstanceRequestDTO circumstanceRequestDTO) {
        Circumstance circumstance = Circumstance.builder()
                .situation(circumstanceRequestDTO.getSituation())
                .emotions(circumstanceRequestDTO.getEmotions())
                .causes(circumstanceRequestDTO.getCauses())
                .conclusion(circumstanceRequestDTO.getConclusion())
                .userId(circumstanceRequestDTO.getUserId())
                .build();

        Circumstance savedCircumstance = circumstanceRepository.save(circumstance);

        return CircumstanceResponseDTO.builder()
                .id(savedCircumstance.getId())
                .situation(savedCircumstance.getSituation())
                .emotions(savedCircumstance.getEmotions())
                .causes(savedCircumstance.getCauses())
                .conclusion(savedCircumstance.getConclusion())
                .createdDate(savedCircumstance.getCreatedTime())
                .userId(savedCircumstance.getUserId())
                .build();
    }

    public CircumstanceResponseDTO getCircumstance(Long id) {
        return circumstanceRepository.findById(id)
                .map(circumstance -> CircumstanceResponseDTO.builder()
                        .id(circumstance.getId())
                        .situation(circumstance.getSituation())
                        .emotions(circumstance.getEmotions())
                        .causes(circumstance.getCauses())
                        .conclusion(circumstance.getConclusion())
                        .createdDate(circumstance.getCreatedTime())
                        .userId(circumstance.getUserId())
                        .build())
                .orElse(null);
    }

    public List<CircumstanceResponseDTO> getAllCircumstances() {
        return circumstanceRepository.findAll().stream()
                .map(circumstance -> CircumstanceResponseDTO.builder()
                        .id(circumstance.getId())
                        .situation(circumstance.getSituation())
                        .emotions(circumstance.getEmotions())
                        .causes(circumstance.getCauses())
                        .conclusion(circumstance.getConclusion())
                        .createdDate(circumstance.getCreatedTime())
                        .userId(circumstance.getUserId())
                        .build())
                .collect(Collectors.toList());
    }
}
