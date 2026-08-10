package com.ansh.quiz_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttemptRequest {

    private Long quizId;
}