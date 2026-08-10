package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.ResultResponse;

public interface ResultService {

    ResultResponse getResult(Long attemptId);
}