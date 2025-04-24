package com.user.ecommerce_project.utils.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetails> handleBusinessException(BusinessException exception) {
        ProblemDetails problemDetails = new ProblemDetails(
                "Business Rule Violation",
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        
        return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetails> handleNotFoundException(NotFoundException exception) {
        ProblemDetails problemDetails = new ProblemDetails(
                "Resource Not Found",
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        
        return new ResponseEntity<>(problemDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationProblemDetails> handleValidationException(ValidationException exception) {
        ValidationProblemDetails problemDetails = new ValidationProblemDetails(
                "Validation Error",
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getValidationErrors()
        );
        
        return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, 
            HttpHeaders headers, 
            HttpStatusCode status, 
            WebRequest request) {
        
        Map<String, String> validationErrors = new HashMap<>();
        
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        ValidationProblemDetails problemDetails = new ValidationProblemDetails(
                "Validation Error",
                "Validation failed for request parameters",
                HttpStatus.BAD_REQUEST.value(),
                validationErrors
        );
        
        return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handleAllExceptions(Exception exception) {
        ProblemDetails problemDetails = new ProblemDetails(
                "Error",
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        
        return new ResponseEntity<>(problemDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
