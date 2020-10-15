package com.soen343.shs.converters.doors;

import com.soen343.shs.dal.model.Door;
import com.soen343.shs.dto.LoadDoorDTO;
import org.springframework.core.convert.converter.Converter;


public class LoadDoorDTOToDoorConverter implements Converter<LoadDoorDTO, Door> {

    @Override
    public Door convert(LoadDoorDTO loadDoorDTO) {
        return Door.builder()
                .open(loadDoorDTO.isOpen())
                .rooms(loadDoorDTO.getRooms())
                .build();
    }
}
