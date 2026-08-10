package com.ansh.quiz_management.controller;

import com.ansh.quiz_management.dto.QuestionRequest;
import com.ansh.quiz_management.dto.QuestionResponse;
import com.ansh.quiz_management.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // CREATE QUESTION - ADMIN ONLY
    @PostMapping
    public QuestionResponse create(
            @Valid @RequestBody QuestionRequest request) {

        return questionService.create(request);
    }

    // GET ALL QUESTIONS
    @GetMapping
    public List<QuestionResponse> getAll() {

        return questionService.getAll();
    }

    // GET QUESTION BY ID
    @GetMapping("/{id}")
    public QuestionResponse getById(
            @PathVariable Long id) {

        return questionService.getById(id);
    }

    // UPDATE QUESTION - ADMIN ONLY
    @PutMapping("/{id}")
    public QuestionResponse update(
            @PathVariable Long id,
            @Valid @RequestBody QuestionRequest request) {

        return questionService.update(id, request);
    }

    // DELETE QUESTION - ADMIN ONLY
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        questionService.delete(id);

        return "Question Deleted Successfully";
    }
}