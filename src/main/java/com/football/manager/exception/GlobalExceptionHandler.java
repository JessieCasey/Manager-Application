package com.football.manager.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleNullReferenceException(EntityNotFoundException ex, WebRequest request) {
        log.error("[handleNullReferenceException] Null': " + request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(new MessageResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUserAlreadyExistException(EntityAlreadyExistException ex, WebRequest request) {
        log.error("[handleUserAlreadyExistException] Null': " + request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value())
                .body(new MessageResponse(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage(), request));
    }

}
