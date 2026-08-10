package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.AnswerRequest;
import com.ansh.quiz_management.dto.AnswerResponse;
import com.ansh.quiz_management.entity.Answer;
import com.ansh.quiz_management.entity.Attempt;
import com.ansh.quiz_management.entity.Option;
import com.ansh.quiz_management.entity.Question;
import com.ansh.quiz_management.repository.AnswerRepository;
import com.ansh.quiz_management.repository.AttemptRepository;
import com.ansh.quiz_management.repository.OptionRepository;
import com.ansh.quiz_management.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AttemptRepository attemptRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public AnswerServiceImpl(
            AnswerRepository answerRepository,
            AttemptRepository attemptRepository,
            QuestionRepository questionRepository,
            OptionRepository optionRepository) {

        this.answerRepository = answerRepository;
        this.attemptRepository = attemptRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public AnswerResponse create(AnswerRequest request) {

        Attempt attempt = attemptRepository.findById(request.getAttemptId())
                .orElseThrow(() -> new RuntimeException("Attempt Not Found"));

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question Not Found"));

        Option option = optionRepository.findById(request.getOptionId())
                .orElseThrow(() -> new RuntimeException("Option Not Found"));

        Answer answer = Answer.builder()
                .attempt(attempt)
                .question(question)
                .selectedOption(option)
                .isCorrect(Boolean.TRUE.equals(option.getCorrect()))
                .build();

        answer = answerRepository.save(answer);

        return AnswerResponse.builder()
                .id(answer.getId())
                .attemptId(attempt.getId())
                .questionId(question.getId())
                .optionId(option.getId())
                .build();
    }

    @Override
    public List<AnswerResponse> getByAttemptId(Long attemptId) {

        return answerRepository.findByAttemptId(attemptId)
                .stream()
                .map(answer -> AnswerResponse.builder()
                        .id(answer.getId())
                        .attemptId(answer.getAttempt().getId())
                        .questionId(answer.getQuestion().getId())
                        .optionId(answer.getSelectedOption().getId())
                        .build())
                .toList();
    }

    @Override
    public AnswerResponse getById(Long id) {

        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer Not Found"));

        return AnswerResponse.builder()
                .id(answer.getId())
                .attemptId(answer.getAttempt().getId())
                .questionId(answer.getQuestion().getId())
                .optionId(answer.getSelectedOption().getId())
                .build();
    }
}