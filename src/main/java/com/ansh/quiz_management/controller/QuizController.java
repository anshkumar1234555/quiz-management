package com.ansh.quiz_management.controller;

import com.ansh.quiz_management.dto.QuizDetailsResponse;
import com.ansh.quiz_management.dto.QuizRequest;
import com.ansh.quiz_management.dto.QuizResponse;
import com.ansh.quiz_management.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // CREATE QUIZ - ADMIN ONLY
    @PostMapping
    public QuizResponse create(
            @Valid @RequestBody QuizRequest request) {

        return quizService.create(request);
    }

    // GET ALL QUIZZES
    @GetMapping
    public List<QuizResponse> getAll() {

        return quizService.getAll();
    }

    // GET QUIZ BY ID
    @GetMapping("/{id}")
    public QuizResponse getById(
            @PathVariable Long id) {

        return quizService.getById(id);
    }

    // UPDATE QUIZ - ADMIN ONLY
    @PutMapping("/{id}")
    public QuizResponse update(
            @PathVariable Long id,
            @Valid @RequestBody QuizRequest request) {

        return quizService.update(id, request);
    }

    // DELETE QUIZ - ADMIN ONLY
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        quizService.delete(id);

        return "Quiz Deleted Successfully";
    }

    // GET QUIZ WITH QUESTIONS AND OPTIONS
    @GetMapping("/{id}/details")
    public QuizDetailsResponse getQuizDetails(
            @PathVariable Long id) {

        return quizService.getQuizDetails(id);
    }
}

