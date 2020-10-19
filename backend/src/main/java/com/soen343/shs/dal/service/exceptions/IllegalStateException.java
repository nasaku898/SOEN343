package com.soen343.shs.dal.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalStateException extends RuntimeException {
    public IllegalStateException() {
        super("Window is blocked.");
    }

    public IllegalStateException(final String msg) {
        super(msg);
    }
}
