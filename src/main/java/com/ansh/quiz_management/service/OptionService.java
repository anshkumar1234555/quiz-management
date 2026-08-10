package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.OptionRequest;
import com.ansh.quiz_management.dto.OptionResponse;

import java.util.List;

public interface OptionService {

    OptionResponse create(OptionRequest request);

    List<OptionResponse> getAll();

    OptionResponse getById(Long id);

    OptionResponse update(Long id, OptionRequest request);

    void delete(Long id);
}