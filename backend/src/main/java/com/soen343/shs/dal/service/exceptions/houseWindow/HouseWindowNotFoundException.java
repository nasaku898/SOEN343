package com.soen343.shs.dal.service.exceptions.houseWindow;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HouseWindowNotFoundException extends RuntimeException{
    public HouseWindowNotFoundException(){
        super("Window not found");
    }

    public HouseWindowNotFoundException(String msg){
        super(msg);
    }
}
