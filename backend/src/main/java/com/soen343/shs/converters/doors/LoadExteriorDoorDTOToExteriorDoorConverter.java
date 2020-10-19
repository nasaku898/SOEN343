package com.soen343.shs.converters.doors;

import com.soen343.shs.dal.model.Door;
import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dto.LoadExteriorDoorDTO;
import org.springframework.core.convert.converter.Converter;

public class LoadExteriorDoorDTOToExteriorDoorConverter implements Converter<LoadExteriorDoorDTO, Door> {
    @Override
    public ExteriorDoor convert(final LoadExteriorDoorDTO dto) {
        return ExteriorDoor.builder()
                .locked(dto.isLocked())
                .open(dto.isOpen())
                .rooms(dto.getRooms())
                .build();
    }
}