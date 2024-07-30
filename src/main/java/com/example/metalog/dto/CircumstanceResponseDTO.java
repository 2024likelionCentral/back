package com.example.metalog.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CircumstanceResponseDTO {
    private Long id;
    private String situation;
    private List<String> recognitions;
    private LocalDateTime createdDate;
}
