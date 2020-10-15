package com.soen343.shs.dal.model;

import java.util.List;

public class InteriorDoor extends Door {
    public InteriorDoor(boolean open, List<Room> rooms) {
        super(open, rooms);
    }
}
