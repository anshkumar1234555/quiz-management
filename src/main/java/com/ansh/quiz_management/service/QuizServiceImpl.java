package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.QuizDetailsResponse;
import com.ansh.quiz_management.dto.QuizRequest;
import com.ansh.quiz_management.dto.QuizResponse;
import com.ansh.quiz_management.entity.Category;
import com.ansh.quiz_management.entity.Quiz;
import com.ansh.quiz_management.repository.CategoryRepository;
import com.ansh.quiz_management.repository.QuestionRepository;
import com.ansh.quiz_management.repository.QuizRepository;
import org.springframework.stereotype.Service;
import com.ansh.quiz_management.dto.QuestionResponse;
import com.ansh.quiz_management.repository.OptionRepository;
import com.ansh.quiz_management.dto.OptionResponse;
import java.util.List;
import com.ansh.quiz_management.dto.QuestionDetailsResponse;
import com.ansh.quiz_management.dto.QuizOptionResponse;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public QuizServiceImpl(QuizRepository quizRepository,
                           CategoryRepository categoryRepository,
                           QuestionRepository questionRepository,
                           OptionRepository optionRepository) {

        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public QuizResponse create(QuizRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        Quiz quiz = Quiz.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(category)
                .build();

        quiz = quizRepository.save(quiz);

        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .categoryName(category.getName())
                .build();
    }

    @Override
    public List<QuizResponse> getAll() {

        return quizRepository.findAll()
                .stream()
                .map(quiz -> QuizResponse.builder()
                        .id(quiz.getId())
                        .title(quiz.getTitle())
                        .description(quiz.getDescription())
                        .categoryName(quiz.getCategory().getName())
                        .build())
                .toList();
    }

    @Override
    public QuizResponse getById(Long id) {

        System.out.println("Requested ID = " + id);

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz Not Found"));

        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .categoryName(quiz.getCategory().getName())
                .build();
    }

    @Override
    public QuizResponse update(Long id, QuizRequest request) {

        System.out.println("Quiz ID = " + id);
        System.out.println("Category ID = " + request.getCategoryId());

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz Not Found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());
        quiz.setCategory(category);

        quiz = quizRepository.save(quiz);

        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .categoryName(category.getName())
                .build();
    }

    @Override
    public void delete(Long id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz Not Found"));

        quizRepository.delete(quiz);
    }

    @Override
    public QuizDetailsResponse getQuizDetails(Long id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz Not Found"));

        List<QuestionDetailsResponse> questions = questionRepository.findByQuizId(id)
                .stream()
                .map(question -> {

                    List<QuizOptionResponse> options = optionRepository
                            .findByQuestionId(question.getId())
                            .stream()
                            .map(option -> QuizOptionResponse.builder()
                                    .id(option.getId())
                                    .optionText(option.getOptionText())
                                    .questionId(question.getId())
                                    .build())
                            .toList();

                    return QuestionDetailsResponse.builder()
                            .id(question.getId())
                            .questionText(question.getQuestionText())
                            .options(options)
                            .build();
                })
                .toList();

        return QuizDetailsResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .categoryName(quiz.getCategory().getName())
                .questions(questions)
                .build();
    }
}