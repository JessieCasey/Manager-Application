package com.football.manager.exception.cover;

import com.football.manager.exception.GlobalExceptionHandler;

/**
 * The PaymentDeclinedException exception extends RuntimeException.
 * GlobalExceptionHandler handles this exception. {@link GlobalExceptionHandler}
 */
public class PaymentDeclinedException extends RuntimeException {
    public PaymentDeclinedException(String message) {
        super(message);
    }
}
