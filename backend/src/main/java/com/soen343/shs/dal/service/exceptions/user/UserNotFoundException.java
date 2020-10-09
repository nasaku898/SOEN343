package com.soen343.shs.dal.service.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User not found");
    }

    public UserNotFoundException(String msg){
        super(msg);
    }
}
