package com.example.metalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Circumstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String situation;
    private String feeling;
    private String cause;
    private String conclusion;
    // 기본 생성자
    public Circumstance() {
    }

    // 모든 필드를 인자로 받는 생성자
    public Circumstance(String situation, String feeling, String cause,String conclusion) {
        this.situation = situation;
        this.feeling = feeling;
        this.cause = cause;
        this.conclusion=conclusion;
    }

}
