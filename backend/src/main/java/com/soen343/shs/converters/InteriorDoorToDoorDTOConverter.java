package com.soen343.shs.converters;

import com.soen343.shs.dal.model.InteriorDoor;
import com.soen343.shs.dto.DoorDTO;
import org.springframework.core.convert.converter.Converter;

public class InteriorDoorToDoorDTOConverter implements Converter<InteriorDoor, DoorDTO> {

    @Override
    public DoorDTO convert(final InteriorDoor door) {
        return DoorDTO.builder()
                .id(door.getId())
                .open(door.isOpen())
                .build();
    }
}
