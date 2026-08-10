package com.ansh.quiz_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testcontroller {

    @GetMapping("/api/test")
    public String test() {
        return "JWT Authentication Successful";
    }
}