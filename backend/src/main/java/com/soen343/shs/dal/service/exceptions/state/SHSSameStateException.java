package com.soen343.shs.dal.service.exceptions.state;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SHSSameStateException extends RuntimeException {
    public SHSSameStateException(final String msg) {
        super(msg);
    }
}
