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

    private String situation;

    @ElementCollection
    @CollectionTable(name = "circumstance_recognitions", joinColumns = @JoinColumn(name = "circumstance_id"))
    @Column(name = "recognition")
    private List<String> recognitions;
}
