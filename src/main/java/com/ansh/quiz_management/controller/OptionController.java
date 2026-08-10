package com.ansh.quiz_management.controller;

import com.ansh.quiz_management.dto.OptionRequest;
import com.ansh.quiz_management.dto.OptionResponse;
import com.ansh.quiz_management.service.OptionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    // CREATE OPTION - ADMIN ONLY
    @PostMapping
    public OptionResponse create(
            @Valid @RequestBody OptionRequest request) {

        return optionService.create(request);
    }

    // GET ALL OPTIONS
    @GetMapping
    public List<OptionResponse> getAll() {

        return optionService.getAll();
    }

    // GET OPTION BY ID
    @GetMapping("/{id}")
    public OptionResponse getById(
            @PathVariable Long id) {

        return optionService.getById(id);
    }

    // UPDATE OPTION - ADMIN ONLY
    @PutMapping("/{id}")
    public OptionResponse update(
            @PathVariable Long id,
            @Valid @RequestBody OptionRequest request) {

        return optionService.update(id, request);
    }

    // DELETE OPTION - ADMIN ONLY
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        optionService.delete(id);

        return "Option Deleted Successfully";
    }
}