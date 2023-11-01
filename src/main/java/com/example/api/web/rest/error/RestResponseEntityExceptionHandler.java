package com.example.api.web.rest.error;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final ModelMapper modelMapper;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, List<String>> errors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.groupingBy(FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

        ex.getBindingResult().getGlobalErrors().forEach(error -> errors.computeIfAbsent(error.getObjectName(), key -> new ArrayList<>()).add(error.getDefaultMessage()));

        ProblemDetail body = ex.updateAndGetBody(this.getMessageSource(), LocaleContextHolder.getLocale());
        body.setProperty("errors", errors);

        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(constraintViolation -> {
            String name = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.computeIfAbsent(name, k -> new ArrayList<>()).add(message);
        });

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        problemDetail.setTitle("Validation failed");
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }
}