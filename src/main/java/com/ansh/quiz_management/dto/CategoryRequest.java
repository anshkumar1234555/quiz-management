package com.ansh.quiz_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CategoryRequest {

    private Long id;

    private String name;

    private String description;

}