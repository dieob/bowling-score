package com.jobsity.challenge.utils;

/**
 * Represents an exception for the bowling game
 *
 * @author Diego Báez
 */
public class BowlingException extends RuntimeException {

    public BowlingException(String message) {
        System.out.println(message);
    }

}