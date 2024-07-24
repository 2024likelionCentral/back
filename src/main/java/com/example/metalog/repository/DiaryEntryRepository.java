package com.example.metalog.repository;

import com.example.metalog.entity.DiaryEntry;
import com.example.metalog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {
    List<DiaryEntry> findByUserAndDate(Long userId, LocalDate date);
}
