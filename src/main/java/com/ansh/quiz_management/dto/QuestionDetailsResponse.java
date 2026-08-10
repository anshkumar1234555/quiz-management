package com.ansh.quiz_management.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDetailsResponse {

    private Long id;
    private String questionText;
    private List<QuizOptionResponse> options;
}