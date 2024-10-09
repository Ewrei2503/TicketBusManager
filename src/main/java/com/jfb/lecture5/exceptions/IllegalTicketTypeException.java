package com.jfb.lecture5.exceptions;

import com.jfb.lecture5.Main;

public class IllegalTicketTypeException extends RuntimeException {
    public static int ticketType = 0;
    public IllegalTicketTypeException(String message) {
        super(message);
        Main.validity = false;
        ticketType++;
    }
    public IllegalTicketTypeException(String message, Throwable cause) {
        super(message, cause);
        Main.validity = false;
        ticketType++;
    }
}
