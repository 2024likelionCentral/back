package com.example.metalog.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoalResponseDTO {
    private Long id;
    private String goal;
    private List<String> actions;
    private LocalDateTime createdTime;
}
