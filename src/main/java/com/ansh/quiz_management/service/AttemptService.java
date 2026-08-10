package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.AttemptRequest;
import com.ansh.quiz_management.dto.AttemptResponse;

import java.util.List;

public interface AttemptService {

    AttemptResponse create(AttemptRequest request);

    List<AttemptResponse> getAll();

    AttemptResponse getById(Long id);

    AttemptResponse submit(Long id);

    void delete(Long id);
}