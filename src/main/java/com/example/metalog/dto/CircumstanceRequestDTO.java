package com.example.metalog.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CircumstanceRequestDTO {
    private String situation;
    private List<String> recognitions;
}
