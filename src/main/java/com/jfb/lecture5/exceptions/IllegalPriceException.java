package com.jfb.lecture5.exceptions;

public class IllegalPriceException extends RuntimeException {
    public static int price = 0;
    public IllegalPriceException(String message) {
        super(message);
        price++;
    }
}
