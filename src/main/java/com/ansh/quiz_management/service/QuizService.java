package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.QuizRequest;
import com.ansh.quiz_management.dto.QuizResponse;
import com.ansh.quiz_management.dto.QuizDetailsResponse;

import java.util.List;

public interface QuizService {

    QuizResponse create(QuizRequest request);

    List<QuizResponse> getAll();

    QuizResponse getById(Long id);

    QuizResponse update(Long id, QuizRequest request);

    QuizDetailsResponse getQuizDetails(Long id);

    void delete(Long id);
}