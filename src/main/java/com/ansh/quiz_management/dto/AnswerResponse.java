package com.ansh.quiz_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponse {

    private Long id;

    private Long attemptId;

    private Long questionId;

    private Long optionId;
}