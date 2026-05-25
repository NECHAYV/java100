package org.exemple.demojava100;

import org.exemple.demojava100.exeption.NotEnoughQuestionsException;
import org.exemple.demojava100.model.Question;
import org.exemple.demojava100.service.QuestionService;
import org.exemple.demojava100.service.impl.ExaminerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final List<Question> testQuestions = Arrays.asList(
            new Question("Q1", "A1"),
            new Question("Q2", "A2"),
            new Question("Q3", "A3")
    );

    @Test
    void shouldReturnExactAmountOfQuestions() {
        when(questionService.getAll()).thenReturn(new HashSet<>(testQuestions));
        when(questionService.getRandomQuestion())
                .thenReturn(testQuestions.get(0), testQuestions.get(1), testQuestions.get(2));

        Collection<Question> result = examinerService.getQuestions(2);
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnUniqueQuestions() {
        when(questionService.getAll()).thenReturn(new HashSet<>(testQuestions));
        // Возвращаем дубликаты, чтобы проверить уникальность
        when(questionService.getRandomQuestion())
                .thenReturn(
                        testQuestions.get(0),
                        testQuestions.get(0),
                        testQuestions.get(1),
                        testQuestions.get(2)
                );

        Collection<Question> result = examinerService.getQuestions(2);
        assertEquals(2, result.size());
    }

    @Test
    void shouldThrowWhenAmountExceedsTotal() {
        when(questionService.getAll()).thenReturn(new HashSet<>(testQuestions));

        assertThrows(NotEnoughQuestionsException.class,
                () -> examinerService.getQuestions(5));
    }

    @Test
    void shouldReturnEmptyCollectionWhenAmountIsZero() {
        when(questionService.getAll()).thenReturn(new HashSet<>(testQuestions));

        Collection<Question> result = examinerService.getQuestions(0);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldHandleAmountEqualToTotal() {
        when(questionService.getAll()).thenReturn(new HashSet<>(testQuestions));
        when(questionService.getRandomQuestion())
                .thenReturn(testQuestions.get(0), testQuestions.get(1), testQuestions.get(2));

        Collection<Question> result = examinerService.getQuestions(3);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(testQuestions));
    }
}
