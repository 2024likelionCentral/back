package com.example.metalog.repository;


import com.example.metalog.entity.Goal;
import com.example.metalog.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    @EntityGraph(attributePaths = "actions")
    Optional<Goal> findById(Long id);
    List<Goal> findAll();
    Goal findByPriorityTrue();
    List<Goal> findAllByUserId(Long userId);
    Optional<Goal> findByIdAndUserId(Long goalId, Long userId);
    Goal findByPriorityTrueAndUserId(Long userId);
    Long countByUserId(Long userId);

}
