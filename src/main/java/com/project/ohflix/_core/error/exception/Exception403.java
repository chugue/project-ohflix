package com.project.ohflix._core.error.exception;

public class Exception403 extends RuntimeException{
    private final Integer errorCode = 403;

    public Exception403(String msg) {
        super(msg);
    }
}
