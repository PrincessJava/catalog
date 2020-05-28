package ru.testproject.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity handleNodataFoundException(
            NoDataFoundException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("date", new Date());
        body.put("message", ex.getMessage());

        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("date", new Date());
        body.put("message", ex.getMessage());

        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }
}
