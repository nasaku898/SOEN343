package com.soen343.shs.dal.service.exceptions.light;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LightIsAlreadyOnException extends RuntimeException {
    public LightIsAlreadyOnException(){
        super("Light is already on.");
    }

    public LightIsAlreadyOnException(String msg){
        super(msg);
    }
}