package com.soen343.shs.converters;

import com.soen343.shs.dal.model.InteriorDoor;
import com.soen343.shs.dto.DoorDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class InteriorDoorToDoorDTOConverter implements Converter<InteriorDoor, DoorDTO> {

    @Override
    public DoorDTO convert(InteriorDoor door) {
        return DoorDTO.builder()
                .id(door.getId())
                .open(door.isOpen())
                .roomIds(door.getRooms().stream().map(r -> r.getId()).collect(Collectors.toSet()))
                .build();
    }
}
