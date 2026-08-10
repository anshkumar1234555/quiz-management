package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.QuestionRequest;
import com.ansh.quiz_management.dto.QuestionResponse;

import java.util.List;

public interface QuestionService {

    QuestionResponse create(QuestionRequest request);

    List<QuestionResponse> getAll();

    QuestionResponse getById(Long id);

    QuestionResponse update(Long id, QuestionRequest request);

    void delete(Long id);
}