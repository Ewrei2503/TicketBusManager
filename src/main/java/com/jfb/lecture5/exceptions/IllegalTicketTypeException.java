package com.jfb.lecture5.exceptions;

public class IllegalTicketTypeException extends RuntimeException {
    public static int ticketType = 0;
    public IllegalTicketTypeException(String message) {
        super(message);
        ticketType++;
    }
}
