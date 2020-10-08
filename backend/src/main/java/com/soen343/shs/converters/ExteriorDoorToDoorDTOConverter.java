package com.soen343.shs.converters;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.DoorDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class ExteriorDoorToDoorDTOConverter implements Converter<ExteriorDoor, DoorDTO> {

    @Override
    public DoorDTO convert(final ExteriorDoor door) {
        return DoorDTO.builder()
                .id(door.getId())
                .open(door.isOpen())
                .roomIds(door.getRooms().stream().map(Room::getId).collect(Collectors.toSet()))
                .locked(door.isLocked())
                .build();
    }
}
