package com.eazybytes.accounts.exceptions;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.eazybytes.accounts.utils.MessageUtils;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// ❗ Handle not found from repository
	@ExceptionHandler({EntityNotFoundException.class, 
					   NoResultException.class,
					   EmptyResultDataAccessException.class})
	public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                MessageUtils.getMessage("entity.notfound"),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
	// ❗ Handle @Valid validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
    	StringBuilder messages = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            messages.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
        );
        return new ResponseEntity<>(
                new ApiErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Validation Error",
                    messages.toString(),
                    request.getRequestURI()
                ),
                HttpStatus.BAD_REQUEST
            );
    }
    // ❗ Handle constraint violations (e.g. duplicate email)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
            new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Data Integrity Violation",
                ex.getMostSpecificCause().getMessage(),
                request.getRequestURI()
            ),
            HttpStatus.BAD_REQUEST
        );
    }
    
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleGeneralException(
            Exception ex, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                MessageUtils.getMessage("server.error.internal"),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
