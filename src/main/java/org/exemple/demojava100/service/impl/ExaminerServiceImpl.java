package org.exemple.demojava100.service.impl;

import org.exemple.demojava100.exeption.NotEnoughQuestionsException;
import org.exemple.demojava100.model.Question;
import org.exemple.demojava100.service.ExaminerService;
import org.exemple.demojava100.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        var allQuestions = questionService.getAll();
        if (amount > allQuestions.size()) {
            throw new NotEnoughQuestionsException(
                    String.format("Requested %d questions but only %d available", amount, allQuestions.size())
            );
        }

        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
