package com.ansh.quiz_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "attempts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    private Double percentage;

    private Integer correctAnswers;

    private Integer incorrectAnswers;

    private Integer unanswered;

    private Long timeTaken;

    private String status;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}