package com.ansh.quiz_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttemptResponse {

    private Long id;

    private Long quizId;

    private Long userId;

    private Integer score;

    private String status;
}