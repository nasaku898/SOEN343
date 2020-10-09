package com.soen343.shs.converters;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dto.HouseLayoutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class HouseToHouseLayoutDTOConverter implements Converter<House, HouseLayoutDTO> {

    @Autowired
    RoomToRoomLayoutDTOConverter roomToRoomLayoutDTOConverter;

    @Override
    public HouseLayoutDTO convert(House house) {
        return HouseLayoutDTO.builder()
                .nbOfRooms(house.getRooms().size())
                .roomLayoutDTOS(roomToRoomLayoutDTOConverter.convert(house.getRooms()))
                .build();
    }
}
