package com.soen343.shs.dal.service.exceptions.houseWindow;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HouseWindowAlreadyOpenedException extends RuntimeException {
    public HouseWindowAlreadyOpenedException(){
        super("Window is already opened.");
    }

    public HouseWindowAlreadyOpenedException(String msg){
        super(msg);
    }
}