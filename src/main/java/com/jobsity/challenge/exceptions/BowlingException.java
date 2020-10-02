package com.jobsity.challenge.exceptions;

/**
 * Represents an exception for the bowling game
 *
 * @author Diego Báez
 */
public class BowlingException extends RuntimeException {

    String message;
    public BowlingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}