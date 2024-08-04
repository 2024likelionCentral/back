package com.example.metalog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "circumstance")
public class Circumstance extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    private String situation;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "circumstance_emotions", joinColumns = @JoinColumn(name = "circumstance_id"))
    @Column(name = "emotion")
    private List<String> emotions;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "circumstance_causes", joinColumns = @JoinColumn(name = "circumstance_id"))
    @Column(name = "cause")
    private List<String> causes;

    private String conclusion;
}
