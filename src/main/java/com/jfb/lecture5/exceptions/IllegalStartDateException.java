package com.jfb.lecture5.exceptions;

public class IllegalStartDateException extends RuntimeException {
    public static int startDate = 0;
    public IllegalStartDateException(String message) {
        super(message);
        startDate++;
    }
}
