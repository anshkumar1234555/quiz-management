package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.CategoryRequest;
import com.ansh.quiz_management.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request);

    List<CategoryResponse> getAll();

    CategoryResponse getById(Long id);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);

}