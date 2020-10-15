package com.soen343.shs.dal.model;

import java.util.Collections;

public class ExteriorDoor extends Door {
    private boolean locked;
    public ExteriorDoor(boolean open, Room room, boolean locked) {
        super(open, Collections.singletonList(room)); // ensures this class will satisfy the parent classes requirement of a list, but that this list will only have 1 single element
        this.locked = locked;
    }
}
