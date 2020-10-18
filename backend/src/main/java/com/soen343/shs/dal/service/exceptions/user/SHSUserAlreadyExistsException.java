package com.soen343.shs.dal.service.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SHSUserAlreadyExistsException extends RuntimeException {
    public SHSUserAlreadyExistsException(final String message) {
        super(message);
    }
}