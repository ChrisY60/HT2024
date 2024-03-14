package com.hacktues.api.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BadCredentialsExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

}
