package com.soen343.shs.dal.service.exceptions.houseWindow;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HouseWindowAlreadyClosedException extends RuntimeException {
    public HouseWindowAlreadyClosedException(){
        super("Window is already closed.");
    }

    public HouseWindowAlreadyClosedException(String msg){
        super(msg);
    }
}
