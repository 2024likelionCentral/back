package com.example.metalog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user_profile")
public class UserProfile extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String motto;

    @Lob
    @Column(name = "profile_picture", columnDefinition = "BLOB")
    private byte[] profilePicture;
}
