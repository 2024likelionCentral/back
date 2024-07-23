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
@Table(name="goal")
public class Goal extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goal;

    @ElementCollection
    @CollectionTable(name = "goal_actions", joinColumns = @JoinColumn(name = "goal_id"))
    @Column(name = "action")
    private List<String> actions;
}


