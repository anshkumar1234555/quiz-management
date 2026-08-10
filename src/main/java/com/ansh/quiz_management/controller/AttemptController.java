package com.ansh.quiz_management.controller;

import com.ansh.quiz_management.dto.AttemptRequest;
import com.ansh.quiz_management.dto.AttemptResponse;
import com.ansh.quiz_management.service.AttemptService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
public class AttemptController {

    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    // START QUIZ
    @PostMapping
    public AttemptResponse create(
            @Valid @RequestBody AttemptRequest request) {

        return attemptService.create(request);
    }

    // GET ALL ATTEMPTS
    @GetMapping
    public List<AttemptResponse> getAll() {

        return attemptService.getAll();
    }

    // GET ATTEMPT BY ID
    @GetMapping("/{id}")
    public AttemptResponse getById(
            @PathVariable Long id) {

        return attemptService.getById(id);
    }

    // SUBMIT QUIZ
    @PutMapping("/{id}/submit")
    public AttemptResponse submit(
            @PathVariable Long id) {

        return attemptService.submit(id);
    }

    // DELETE ATTEMPT
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        attemptService.delete(id);

        return "Attempt Deleted Successfully";
    }
}