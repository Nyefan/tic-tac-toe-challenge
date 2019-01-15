package com.nyefan.anaconda.exception;

import org.springframework.http.HttpStatus;

public final class TicTacToeException extends RuntimeException {
    private final HttpStatus status;

    public TicTacToeException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public TicTacToeException(String message, Throwable cause) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
    }

    public TicTacToeException(HttpStatus status, String message) {
        this(status, message, null);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
