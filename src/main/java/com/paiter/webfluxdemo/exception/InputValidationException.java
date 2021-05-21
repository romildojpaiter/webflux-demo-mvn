package com.paiter.webfluxdemo.exception;

public class InputValidationException extends RuntimeException {

    private static final String MGS = "allowed range is 10 -20";
    private static final int errorCode = 100;
    private final int input;

    public InputValidationException(int input) {
        super(MGS);
        this.input = input;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getInput() {
        return input;
    }
}
