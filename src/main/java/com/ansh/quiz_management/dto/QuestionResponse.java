package com.ansh.quiz_management.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {

    private Long id;
    private String questionText;
    private String quizTitle;
    private List<OptionResponse> options;
}