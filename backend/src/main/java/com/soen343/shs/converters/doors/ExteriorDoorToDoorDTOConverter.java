package com.soen343.shs.converters.doors;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dto.DoorDTO;
import org.springframework.core.convert.converter.Converter;

public class ExteriorDoorToDoorDTOConverter implements Converter<ExteriorDoor, DoorDTO> {

    @Override
    public DoorDTO convert(final ExteriorDoor door) {
        return DoorDTO.builder()
                .id(door.getId())
                .open(door.isOpen())
                .locked(door.isLocked())
                .rooms(door.getRooms())
                .build();
    }
}
