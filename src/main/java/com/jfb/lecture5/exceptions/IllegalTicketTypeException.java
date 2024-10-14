package com.jfb.lecture5.exceptions;

import com.jfb.lecture5.Main;

public class IllegalTicketTypeException extends RuntimeException {
    public static int countOfIllegalTicketType = 0;
    public IllegalTicketTypeException(String message) {
        super(message);
        Main.isValid = false;
        countOfIllegalTicketType++;
    }
    public IllegalTicketTypeException(String message, Throwable cause) {
        super(message, cause);
        Main.isValid = false;
        countOfIllegalTicketType++;
    }
}
