package com.ansh.quiz_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizOptionResponse {

    private Long id;
    private String optionText;
    private Long questionId;
}