package com.example.metalog.dto;

import com.example.metalog.entity.User;
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
    private  boolean priority;
    private Long userId;
}
