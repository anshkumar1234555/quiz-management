package com.ansh.quiz_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String questionText;

    private Integer marks;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    private String difficulty;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}