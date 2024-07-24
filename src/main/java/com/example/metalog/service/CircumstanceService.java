package com.example.metalog.service;

import com.example.metalog.entity.Circumstance;
import com.example.metalog.repository.CircumstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CircumstanceService {

    private final CircumstanceRepository repository;

    @Autowired
    public CircumstanceService(CircumstanceRepository repository) {
        this.repository = repository;
    }

    public Circumstance saveCircumstance(Circumstance circumstance) {
        return repository.save(circumstance);
    }
}
