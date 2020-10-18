package com.soen343.shs.dal.service.exceptions.exteriorDoor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExteriorDoorAlreadyUnlockedException extends RuntimeException{
    public ExteriorDoorAlreadyUnlockedException() {
        super("Error: Exterior door is already unlocked.");
    }

    public ExteriorDoorAlreadyUnlockedException(String message) {
        super(message);
    }
}
