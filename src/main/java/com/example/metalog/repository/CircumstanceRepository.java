package com.example.metalog.repository;

import com.example.metalog.entity.Circumstance;
import com.example.metalog.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

public interface CircumstanceRepository extends JpaRepository<Circumstance, Long> {

    Optional<Circumstance> findByIdAndUserId(Long id, Long userId);
    List<Circumstance> findAllByUserId(Long userId);

}
