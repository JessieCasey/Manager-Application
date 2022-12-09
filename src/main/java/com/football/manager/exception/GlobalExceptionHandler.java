package com.football.manager.exception;

import com.football.manager.exception.cover.EntityAlreadyExistException;
import com.football.manager.exception.cover.EntityNotFoundException;
import com.football.manager.exception.cover.PaymentDeclinedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * GlobalExceptionHandler handles exceptions
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleNullReferenceException(EntityNotFoundException ex, WebRequest request) {
        log.error("[handleNullReferenceException]': " + request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(new MessageResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUserAlreadyExistException(EntityAlreadyExistException ex, WebRequest request) {
        log.error("[handleUserAlreadyExistException]': " + request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value())
                .body(new MessageResponse(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage(), request));
    }

    @ExceptionHandler
    public ResponseEntity<?> handlePaymentDeclinedException(PaymentDeclinedException ex, WebRequest request) {
        log.error("[handlePaymentDeclinedException]: " + request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value())
                .body(new MessageResponse(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage(), request));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        log.error("[handlePaymentDeclinedException]: " + request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new MessageResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception ex, WebRequest request) {
        log.error("[handlePaymentDeclinedException]: " + request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(new MessageResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request));
    }
}
