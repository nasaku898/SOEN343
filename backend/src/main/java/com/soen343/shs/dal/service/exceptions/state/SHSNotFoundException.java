package com.soen343.shs.dal.service.exceptions.state;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SHSNotFoundException extends RuntimeException {

    public SHSNotFoundException(final String msg) {
        super(msg);
    }
}
