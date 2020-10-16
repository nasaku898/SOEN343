package com.soen343.shs.dal.service.exceptions.room;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoomNotFoundException extends RuntimeException{
    public RoomNotFoundException(){
        super("Room not found");
    }

    public RoomNotFoundException(String msg){
        super(msg);
    }
}
