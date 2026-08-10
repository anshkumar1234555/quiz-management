package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.AttemptRequest;
import com.ansh.quiz_management.dto.AttemptResponse;
import com.ansh.quiz_management.entity.Attempt;
import com.ansh.quiz_management.entity.Quiz;
import com.ansh.quiz_management.entity.User;
import com.ansh.quiz_management.repository.AttemptRepository;
import com.ansh.quiz_management.repository.QuizRepository;
import com.ansh.quiz_management.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.ansh.quiz_management.entity.Answer;
import java.util.List;
import com.ansh.quiz_management.repository.AnswerRepository;
import org.springframework.security.core.context.SecurityContextHolder;


@Service
public class AttemptServiceImpl implements AttemptService {

    private final AttemptRepository attemptRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    public AttemptServiceImpl(AttemptRepository attemptRepository,
                              QuizRepository quizRepository,
                              UserRepository userRepository,
                              AnswerRepository answerRepository) {

        this.attemptRepository = attemptRepository;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public AttemptResponse create(AttemptRequest request) {

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz Not Found"));

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Attempt attempt = Attempt.builder()
                .quiz(quiz)
                .user(user)
                .score(0)
                .status("STARTED")
                .build();

        attempt = attemptRepository.save(attempt);

        return AttemptResponse.builder()
                .id(attempt.getId())
                .quizId(quiz.getId())
                .userId(user.getId())
                .score(attempt.getScore())
                .status(attempt.getStatus())
                .build();
    }
    @Override
    public List<AttemptResponse> getAll() {

        return attemptRepository.findAll()
                .stream()
                .map(attempt -> AttemptResponse.builder()
                        .id(attempt.getId())
                        .quizId(attempt.getQuiz().getId())
                        .userId(attempt.getUser().getId())
                        .score(attempt.getScore())
                        .status(attempt.getStatus())
                        .build())
                .toList();
    }

    @Override
    public AttemptResponse getById(Long id) {

        Attempt attempt = attemptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attempt Not Found"));

        return AttemptResponse.builder()
                .id(attempt.getId())
                .quizId(attempt.getQuiz().getId())
                .userId(attempt.getUser().getId())
                .score(attempt.getScore())
                .status(attempt.getStatus())
                .build();
    }
    @Override
    public AttemptResponse submit(Long id) {

        Attempt attempt = attemptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attempt Not Found"));

        List<Answer> answers = answerRepository.findAll()
                .stream()
                .filter(answer -> answer.getAttempt().getId().equals(id))
                .toList();

        int score = 0;

        for (Answer answer : answers) {

            if (Boolean.TRUE.equals(answer.getSelectedOption().getCorrect())) {
                score++;
            }
        }

        attempt.setScore(score);
        attempt.setStatus("SUBMITTED");

        attempt = attemptRepository.save(attempt);

        return AttemptResponse.builder()
                .id(attempt.getId())
                .quizId(attempt.getQuiz().getId())
                .userId(attempt.getUser().getId())
                .score(attempt.getScore())
                .status(attempt.getStatus())
                .build();
    }
    @Override
    public void delete(Long id) {

        Attempt attempt = attemptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attempt Not Found"));

        attemptRepository.delete(attempt);
    }
}