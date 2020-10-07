package com.soen343.shs.converters;

import com.soen343.shs.dal.model.Door;
import com.soen343.shs.dal.model.InteriorDoor;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.RoomDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;

public class InteriorDoorToDoorDTOConverter implements Converter<InteriorDoor, DoorDTO> {

    @Override
    public DoorDTO convert(InteriorDoor door) {
        return DoorDTO.builder()
                .id(door.getId())
                .open(door.isOpen())
                .roomIds(
                .build()
    }
}
