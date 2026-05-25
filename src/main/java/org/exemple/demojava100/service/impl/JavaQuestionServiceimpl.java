package org.exemple.demojava100.service.impl;

import org.exemple.demojava100.model.Question;
import org.exemple.demojava100.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionServiceimpl implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new NoSuchElementException("No questions available");
        }
        List<Question> list = new ArrayList<>(questions);
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}