package com.soen343.shs.dal.service.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HouseMemberNotFoundException extends RuntimeException {
    public HouseMemberNotFoundException() {
        super("HouseMember not found");
    }

    public HouseMemberNotFoundException(final String msg){
        super(msg);
    }
}
