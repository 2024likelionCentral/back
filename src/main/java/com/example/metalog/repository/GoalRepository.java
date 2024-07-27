package com.example.metalog.repository;


import com.example.metalog.entity.Goal;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    @EntityGraph(attributePaths = "actions")
    Optional<Goal> findById(Long id);
}
