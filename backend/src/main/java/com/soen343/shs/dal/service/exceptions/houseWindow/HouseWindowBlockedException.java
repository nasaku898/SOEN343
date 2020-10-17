package com.soen343.shs.dal.service.exceptions.houseWindow;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HouseWindowBlockedException extends RuntimeException{
    public HouseWindowBlockedException(){
        super("Window is blocked.");
    }

    public HouseWindowBlockedException(String msg){
        super(msg);
    }
}
