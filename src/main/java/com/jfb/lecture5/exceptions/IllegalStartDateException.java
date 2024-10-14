package com.jfb.lecture5.exceptions;

import com.jfb.lecture5.Main;

public class IllegalStartDateException extends RuntimeException {
    public static int countOfIllegalStartDate = 0;
    public IllegalStartDateException(String message) {
        super(message);
        Main.isValid = false;
        countOfIllegalStartDate++;
    }
}
