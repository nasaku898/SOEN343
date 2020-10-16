package com.soen343.shs.dal.service.exceptions.house;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HouseNotFoundException extends RuntimeException {
    public HouseNotFoundException(){
        super("House not found");
    }

    public HouseNotFoundException(String msg){
        super(msg);
    }
}
