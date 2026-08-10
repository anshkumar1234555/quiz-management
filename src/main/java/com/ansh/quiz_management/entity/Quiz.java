package com.ansh.quiz_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String difficulty;

    private Integer duration;

    private Integer passingScore;

    private Integer maxAttempts;

    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}