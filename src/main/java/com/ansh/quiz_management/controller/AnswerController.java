package com.ansh.quiz_management.controller;

import com.ansh.quiz_management.dto.AnswerRequest;
import com.ansh.quiz_management.dto.AnswerResponse;
import com.ansh.quiz_management.service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // SAVE STUDENT ANSWER
    @PostMapping
    public AnswerResponse create(
            @Valid @RequestBody AnswerRequest request) {

        return answerService.create(request);
    }

    // GET ANSWERS OF AN ATTEMPT
    @GetMapping("/attempt/{attemptId}")
    public List<AnswerResponse> getByAttemptId(
            @PathVariable Long attemptId) {

        return answerService.getByAttemptId(attemptId);
    }

    // GET ANSWER BY ID
    @GetMapping("/{id}")
    public AnswerResponse getById(
            @PathVariable Long id) {

        return answerService.getById(id);
    }
}