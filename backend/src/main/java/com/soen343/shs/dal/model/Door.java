package com.soen343.shs.dal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Door {
    enum Type {
        INDOOR,
        OUTDOOR
    }

    private double height;
    private double width;
    private Type type;
    private boolean open;
    private Room location;
}
