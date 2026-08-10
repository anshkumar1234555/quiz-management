package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.AnswerRequest;
import com.ansh.quiz_management.dto.AnswerResponse;

import java.util.List;

public interface AnswerService {

    AnswerResponse create(AnswerRequest request);

    List<AnswerResponse> getByAttemptId(Long attemptId);

    AnswerResponse getById(Long id);
}