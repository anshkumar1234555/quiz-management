package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.CategoryRequest;
import com.ansh.quiz_management.dto.CategoryResponse;
import com.ansh.quiz_management.entity.Category;
import com.ansh.quiz_management.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        category = categoryRepository.save(category);

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public List<CategoryResponse> getAll() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .build())
                .toList();
    }

    @Override
    public CategoryResponse getById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        category = categoryRepository.save(category);

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
    @Override
    public void delete(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        categoryRepository.delete(category);
    }
}