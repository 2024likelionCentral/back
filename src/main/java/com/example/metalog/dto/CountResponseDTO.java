package com.example.metalog.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountResponseDTO {
    private Long circumstancesCount;
    private Long goalsCount;
}
