package com.soen343.shs.dal.service.exceptions.light;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LightIsAlreadyOffException extends RuntimeException {
    public LightIsAlreadyOffException(){
        super("Light is already off.");
    }

    public LightIsAlreadyOffException(String msg){
        super(msg);
    }
}