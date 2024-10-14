package com.jfb.lecture5.exceptions;

import com.jfb.lecture5.Main;

public class IllegalPriceException extends RuntimeException {
    public static int countOfIllegalPrice = 0;
    public IllegalPriceException(String message) {
        super(message);
        Main.isValid = false;
        countOfIllegalPrice++;
    }
}
