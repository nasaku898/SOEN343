package com.soen343.shs.dal.service.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SHSUserAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -3799906447954225609L;

    public SHSUserAlreadyExistsException(final String message) {
        super(message);
    }
}