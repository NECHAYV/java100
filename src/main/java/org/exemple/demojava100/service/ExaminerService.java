package org.exemple.demojava100.service;

import org.exemple.demojava100.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
