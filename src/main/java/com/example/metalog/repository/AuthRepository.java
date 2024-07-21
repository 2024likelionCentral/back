package com.example.metalog.repository;

import com.example.metalog.entity.Auth;
import com.example.metalog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    boolean existsByUser(User user);

    Optional<Auth> findByRefreshToken(String refreshToken);
}
