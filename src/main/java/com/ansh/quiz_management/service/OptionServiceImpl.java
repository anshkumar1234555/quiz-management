package com.ansh.quiz_management.service;

import com.ansh.quiz_management.dto.OptionRequest;
import com.ansh.quiz_management.dto.OptionResponse;
import com.ansh.quiz_management.entity.Option;
import com.ansh.quiz_management.entity.Question;
import com.ansh.quiz_management.repository.OptionRepository;
import com.ansh.quiz_management.repository.QuestionRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    public OptionServiceImpl(OptionRepository optionRepository,
                             QuestionRepository questionRepository) {
        this.optionRepository = optionRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public OptionResponse create(OptionRequest request) {

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question Not Found"));

        Option option = Option.builder()
                .optionText(request.getOptionText())
                .correct(request.getCorrect())
                .question(question)
                .build();

        option = optionRepository.save(option);

        return OptionResponse.builder()
                .id(option.getId())
                .optionText(option.getOptionText())
                .correct(option.getCorrect())
                .questionId(question.getId())
                .build();
    }
    @Override
    public List<OptionResponse> getAll() {

        return optionRepository.findAll()
                .stream()
                .map(option -> OptionResponse.builder()
                        .id(option.getId())
                        .optionText(option.getOptionText())
                        .correct(option.getCorrect())
                        .questionId(option.getQuestion().getId())
                        .build())
                .toList();
    }

    @Override
    public OptionResponse getById(Long id) {

        Option option = optionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Option Not Found"));

        return OptionResponse.builder()
                .id(option.getId())
                .optionText(option.getOptionText())
                .correct(option.getCorrect())
                .questionId(option.getQuestion().getId())
                .build();
    }

    @Override
    public OptionResponse update(Long id, OptionRequest request) {

        Option option = optionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Option Not Found"));

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question Not Found"));

        option.setOptionText(request.getOptionText());
        option.setCorrect(request.getCorrect());
        option.setQuestion(question);

        option = optionRepository.save(option);

        return OptionResponse.builder()
                .id(option.getId())
                .optionText(option.getOptionText())
                .correct(option.getCorrect())
                .questionId(question.getId())
                .build();
    }

    @Override
    public void delete(Long id) {

        Option option = optionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Option Not Found"));

        optionRepository.delete(option);
    }
}