package com.soen343.shs.converters.doors;

import com.soen343.shs.dal.model.Door;
import com.soen343.shs.dal.model.InteriorDoor;
import com.soen343.shs.dto.LoadInteriorDoorDTO;
import org.springframework.core.convert.converter.Converter;

public class LoadInteriorDoorDTOToInteriorDoorConverter implements Converter<LoadInteriorDoorDTO, Door> {
    @Override
    public InteriorDoor convert(final LoadInteriorDoorDTO dto) {
        return InteriorDoor.builder()
                .open(dto.isOpen())
                .build();
    }
}