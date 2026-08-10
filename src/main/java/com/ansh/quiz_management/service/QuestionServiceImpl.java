package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.QuestionRequest;
import com.ansh.quiz_management.dto.QuestionResponse;
import com.ansh.quiz_management.repository.QuestionRepository;
import com.ansh.quiz_management.repository.QuizRepository;
import org.springframework.stereotype.Service;
import com.ansh.quiz_management.entity.Question;
import com.ansh.quiz_management.entity.Quiz;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository,
                               QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public QuestionResponse create(QuestionRequest request) {

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz Not Found"));

        Question question = Question.builder()
                .questionText(request.getQuestionText())
                .quiz(quiz)
                .build();

        question = questionRepository.save(question);

        return QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .quizTitle(quiz.getTitle())
                .build();
    }

    @Override
    public List<QuestionResponse> getAll() {

        return questionRepository.findAll()
                .stream()
                .map(question -> QuestionResponse.builder()
                        .id(question.getId())
                        .questionText(question.getQuestionText())
                        .quizTitle(question.getQuiz().getTitle())
                        .build())
                .toList();
    }
    @Override
    public QuestionResponse getById(Long id) {

        System.out.println("Question ID received = " + id);

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question Not Found"));

        return QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .quizTitle(question.getQuiz().getTitle())
                .build();
    }
    @Override
    public QuestionResponse update(Long id, QuestionRequest request) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question Not Found"));

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz Not Found"));

        question.setQuestionText(request.getQuestionText());
        question.setQuiz(quiz);

        question = questionRepository.save(question);

        return QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .quizTitle(quiz.getTitle())
                .build();
    }

    @Override
    public void delete(Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question Not Found"));

        questionRepository.delete(question);
    }
}