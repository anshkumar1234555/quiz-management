package com.ansh.quiz_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Long categoryId;
}