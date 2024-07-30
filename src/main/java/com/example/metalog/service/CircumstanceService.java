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
                .recognitions(circumstanceRequestDTO.getRecognitions())
                .build();

        Circumstance savedCircumstance = circumstanceRepository.save(circumstance);

        return CircumstanceResponseDTO.builder()
                .id(savedCircumstance.getId())
                .situation(savedCircumstance.getSituation())
                .recognitions(savedCircumstance.getRecognitions())
                .createdDate(savedCircumstance.getCreatedTime())
                .build();
    }

    public CircumstanceResponseDTO getCircumstance(Long id) {
        return circumstanceRepository.findById(id)
                .map(circumstance -> CircumstanceResponseDTO.builder()
                        .id(circumstance.getId())
                        .situation(circumstance.getSituation())
                        .recognitions(circumstance.getRecognitions())
                        .createdDate(circumstance.getCreatedTime())
                        .build())
                .orElse(null);
    }

    public List<CircumstanceResponseDTO> getAllCircumstances() {
        return circumstanceRepository.findAll().stream()
                .map(circumstance -> CircumstanceResponseDTO.builder()
                        .id(circumstance.getId())
                        .situation(circumstance.getSituation())
                        .recognitions(circumstance.getRecognitions())
                        .createdDate(circumstance.getCreatedTime())
                        .build())
                .collect(Collectors.toList());
    }
}
