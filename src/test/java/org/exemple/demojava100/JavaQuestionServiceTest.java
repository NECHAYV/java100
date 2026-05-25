package org.exemple.demojava100;

import org.exemple.demojava100.model.Question;
import org.exemple.demojava100.service.impl.JavaQuestionServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionServiceimpl service;

    @BeforeEach
    void setUp() {
        service = new JavaQuestionServiceimpl();
    }

    @Test
    void shouldAddQuestionByStrings() {
        Question q = service.add("Q1", "A1");
        assertNotNull(q);
        assertEquals("Q1", q.getQuestion());
        assertEquals("A1", q.getAnswer());
        assertTrue(service.getAll().contains(q));
    }

    @Test
    void shouldAddQuestionObject() {
        Question q = new Question("Q2", "A2");
        Question result = service.add(q);
        assertSame(q, result);
        assertTrue(service.getAll().contains(q));
    }

    @Test
    void shouldRemoveExistingQuestion() {
        Question q = service.add("Q3", "A3");
        Question removed = service.remove(q);
        assertNotNull(removed);
        assertEquals(q, removed);
        assertFalse(service.getAll().contains(q));
    }

    @Test
    void shouldReturnNullWhenRemovingNonExisting() {
        Question q = new Question("NON", "EXIST");
        assertNull(service.remove(q));
    }

    @Test
    void shouldGetAllQuestions() {
        service.add("Q1", "A1");
        service.add("Q2", "A2");
        Collection<Question> all = service.getAll();
        assertEquals(2, all.size());
    }

    @Test
    void shouldReturnEmptyCollectionWhenEmpty() {
        assertTrue(service.getAll().isEmpty());
    }

    @Test
    void shouldGetRandomQuestion() {
        service.add("Q1", "A1");
        service.add("Q2", "A2");
        Question random = service.getRandomQuestion();
        assertNotNull(random);
        assertTrue(service.getAll().contains(random));
    }

    @Test
    void shouldThrowWhenRandomOnEmptyCollection() {
        assertThrows(NoSuchElementException.class, () -> service.getRandomQuestion());
    }

    @Test
    void shouldNotAddDuplicate() {
        Question q1 = service.add("Q", "A");
        Question q2 = service.add("Q", "A");
        assertEquals(1, service.getAll().size());
        assertEquals(q1, q2); // add возвращает тот же объект, так как Set не изменяется
    }
}
