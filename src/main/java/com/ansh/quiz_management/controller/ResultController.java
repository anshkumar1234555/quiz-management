package com.ansh.quiz_management.controller;

import com.ansh.quiz_management.dto.ResultResponse;
import com.ansh.quiz_management.service.ResultService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    // GET FINAL RESULT
    @GetMapping("/{attemptId}")
    public ResultResponse getResult(
            @PathVariable Long attemptId) {

        return resultService.getResult(attemptId);
    }
}