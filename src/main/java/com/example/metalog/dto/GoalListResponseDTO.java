package com.example.metalog.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GoalListResponseDTO {
    private List<GoalResponseDTO> goals;
    private int totalGoals;
}
