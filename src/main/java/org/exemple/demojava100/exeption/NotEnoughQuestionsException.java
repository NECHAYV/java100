package org.exemple.demojava100.exeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughQuestionsException extends RuntimeException {
    public NotEnoughQuestionsException(String message) {
        super(message);
    }
}
