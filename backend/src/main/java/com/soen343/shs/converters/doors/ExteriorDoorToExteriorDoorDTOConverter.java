package com.soen343.shs.converters.doors;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dto.ExteriorDoorDTO;
import org.springframework.core.convert.converter.Converter;

public class ExteriorDoorToExteriorDoorDTOConverter implements Converter<ExteriorDoor, ExteriorDoorDTO> {

    @Override
    public ExteriorDoorDTO convert(final ExteriorDoor door) {
        return ExteriorDoorDTO.builder()
                .locked(door.getLocked())
                .id(door.getId())
                .open(door.getOpen())
                .build();
    }
}
