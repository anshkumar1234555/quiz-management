package com.ansh.quiz_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultResponse {

    private Long attemptId;
    private Long quizId;
    private Long userId;

    private int score;
    private int totalQuestions;

    private double percentage;

    private String status;
}