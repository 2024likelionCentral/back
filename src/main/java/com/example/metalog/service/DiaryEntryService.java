package com.example.metalog.service;

import com.example.metalog.dto.DiaryEntryResponseDTO;
import com.example.metalog.dto.UserResponseDTO;
import com.example.metalog.entity.DiaryEntry;
import com.example.metalog.repository.DiaryEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class DiaryEntryService {

    private final DiaryEntryRepository diaryEntryRepository;

    public List<DiaryEntryResponseDTO> findEntriesByDate(Long userId, LocalDate date) {
        List<DiaryEntry> entries = diaryEntryRepository.findByUserAndDate(userId, date);
        return entries.stream()
                .map(DiaryEntryResponseDTO::new)
                .collect(Collectors.toList());
    }
}

