package com.soen343.shs.converters.doors;

import com.soen343.shs.dal.model.InteriorDoor;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.InteriorDoorDTO;
import org.springframework.core.convert.converter.Converter;

public class InteriorDoorToInteriorDoorDTOConverter implements Converter<InteriorDoor, DoorDTO> {

    @Override
    public InteriorDoorDTO convert(final InteriorDoor door) {
        return InteriorDoorDTO.builder()
                .id(door.getId())
                .open(door.getOpen())
                .build();
    }
}
