package com.example.metalog.dto;

import com.example.metalog.entity.DiaryEntry;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryEntryResponseDTO {
    private Long id;
    private String content;
    private LocalDate date;

    public DiaryEntryResponseDTO(DiaryEntry entry) {
        this.id = entry.getId();
        this.content = entry.getContent();
        this.date = entry.getDate();
    }
}

