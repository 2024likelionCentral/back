package com.example.metalog.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CircumstanceListResponseDTO {
    private List<CircumstanceResponseDTO> circumstances;
    private int totalCircumstances;
}
