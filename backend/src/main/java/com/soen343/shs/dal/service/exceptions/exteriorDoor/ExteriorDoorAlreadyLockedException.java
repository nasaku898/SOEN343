package com.soen343.shs.dal.service.exceptions.exteriorDoor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExteriorDoorAlreadyLockedException extends RuntimeException{
    public ExteriorDoorAlreadyLockedException() {
        super("Error: Exterior door is already locked.");
    }

    public ExteriorDoorAlreadyLockedException(String message) {
        super(message);
    }
}
