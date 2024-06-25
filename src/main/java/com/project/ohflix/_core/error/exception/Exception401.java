package com.project.ohflix._core.error.exception;

public class Exception401 extends RuntimeException{
    private final Integer errorCode = 401;

    public Exception401(String msg) {
        super(msg);
    }
}
