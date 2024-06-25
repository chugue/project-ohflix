package com.project.ohflix._core.error.exception;

public class Exception404 extends RuntimeException{
    private final Integer errorCode = 404;

    public Exception404(String msg) {
        super(msg);
    }
}
