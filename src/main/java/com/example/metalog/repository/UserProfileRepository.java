package com.example.metalog.repository;

import com.example.metalog.entity.UserProfile;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    public Optional<UserProfile> findByUsername(String username);


}
