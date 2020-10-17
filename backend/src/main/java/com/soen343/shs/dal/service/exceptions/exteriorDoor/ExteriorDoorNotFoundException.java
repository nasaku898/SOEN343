package com.soen343.shs.dal.service.exceptions.exteriorDoor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExteriorDoorNotFoundException extends RuntimeException {
    public ExteriorDoorNotFoundException() {
        super("Exterior door not found.");
    }

    public ExteriorDoorNotFoundException(String message) {
        super(message);
    }
}
