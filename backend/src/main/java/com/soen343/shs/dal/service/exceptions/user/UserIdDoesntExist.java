package com.soen343.shs.dal.service.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserIdDoesntExist extends RuntimeException {
    public UserIdDoesntExist(final String msg) {
        super(msg);
    }
}
