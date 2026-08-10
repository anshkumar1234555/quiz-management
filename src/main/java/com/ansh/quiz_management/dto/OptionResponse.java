package com.ansh.quiz_management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionResponse {

    private Long id;

    private String optionText;

    private Boolean correct;

    private Long questionId;
}