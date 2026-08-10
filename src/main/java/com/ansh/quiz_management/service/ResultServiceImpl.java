package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.ResultResponse;
import com.ansh.quiz_management.entity.Attempt;
import com.ansh.quiz_management.entity.User;
import com.ansh.quiz_management.repository.AttemptRepository;
import com.ansh.quiz_management.repository.QuestionRepository;
import com.ansh.quiz_management.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

    private final AttemptRepository attemptRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public ResultServiceImpl(
            AttemptRepository attemptRepository,
            QuestionRepository questionRepository,
            UserRepository userRepository) {

        this.attemptRepository = attemptRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResultResponse getResult(Long attemptId) {

        Attempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt Not Found"));

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_ADMIN"));


        if (!isAdmin &&
                !attempt.getUser().getId().equals(loggedInUser.getId())) {

            throw new RuntimeException("Access Denied");
        }

        int totalQuestions = questionRepository
                .findByQuizId(attempt.getQuiz().getId())
                .size();

        int score = attempt.getScore();

        double percentage = 0;

        if (totalQuestions > 0) {
            percentage = ((double) score / totalQuestions) * 100;
        }

        return ResultResponse.builder()
                .attemptId(attempt.getId())
                .quizId(attempt.getQuiz().getId())
                .userId(attempt.getUser().getId())
                .score(score)
                .totalQuestions(totalQuestions)
                .percentage(percentage)
                .status(attempt.getStatus())
                .build();
    }
}