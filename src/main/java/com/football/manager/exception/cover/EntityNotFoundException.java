package com.football.manager.exception.cover;

import com.football.manager.exception.GlobalExceptionHandler;

/**
 * The EntityNotFoundException exception extends RuntimeException.
 * GlobalExceptionHandler handles this exception. {@link GlobalExceptionHandler}
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
