package com.football.manager.exception;

/**
 * The EntityAlreadyExistException exception extends RuntimeException.
 * GlobalExceptionHandler handles this exception. {@link GlobalExceptionHandler}
 */

public class EntityAlreadyExistException extends RuntimeException {
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
