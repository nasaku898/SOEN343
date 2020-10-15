package com.soen343.shs.dal.service.exceptions.light;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LightNotFoundException extends RuntimeException {
    public LightNotFoundException() {
        super("Light not found.");
    }

    public LightNotFoundException(String message) {
        super(message);
    }
}
