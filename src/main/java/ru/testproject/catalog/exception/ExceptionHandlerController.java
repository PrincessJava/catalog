package ru.testproject.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity handleNodataFoundException(
            NoDataFoundException ex, WebRequest request) {

        Map<String, Object> body = createBodyMessage(ex.getMessage());

        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleValidationExceptions(
            ValidationException ex) {
        Map<String, Object> body = createBodyMessage(ex.getMessage());

        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationExceptions(
            ConstraintViolationException ex) {
        Map<String, Object> body = createBodyMessage(ex.getMessage());

        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> createBodyMessage(String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("date", new Date());
        body.put("message", message);
        return body;
    }
}
