package com.devsuperior.userdept.controllers.exceptions;

import com.devsuperior.userdept.services.exceptions.UserNotFoundException;
import com.devsuperior.userdept.services.exceptions.UserNullFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    private StandardError standardError = new StandardError();
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> userNotFound(UserNotFoundException ex, HttpServletRequest request) {
        standardError.setTimeStamp(LocalDateTime.now());
        standardError.setStatus(HttpStatus.NOT_FOUND.value());
        standardError.setError("User not found");
        standardError.setMessage(ex.getMessage());
        standardError.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(UserNullFieldsException.class)
    public ResponseEntity<StandardError> userNullField(UserNullFieldsException ex, HttpServletRequest request) {
        standardError.setTimeStamp(LocalDateTime.now());
        standardError.setStatus(HttpStatus.BAD_REQUEST.value());
        standardError.setError("Null fields");
        standardError.setMessage(ex.getMessage());
        standardError.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
}
