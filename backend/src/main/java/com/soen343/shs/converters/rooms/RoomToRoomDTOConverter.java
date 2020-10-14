package com.soen343.shs.converters.rooms;

import com.soen343.shs.dal.model.*;
import com.soen343.shs.dto.RoomDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class RoomToRoomDTOConverter implements Converter<Room, RoomDTO> {

    @Override
    public RoomDTO convert(final Room room) {
        return RoomDTO.builder()
                .doorIds(room.getDoors().stream().map(Door::getId).collect(Collectors.toSet()))
                .lightIds(room.getLights().stream().map(Light::getId).collect(Collectors.toSet()))
                .name(room.getName())
                .temperature(room.getTemperature())
                .windowIds(room.getHouseWindows().stream().map(HouseWindow::getId).collect(Collectors.toSet()))
                .userIds(room.getUserIds())
                .build();
    }
}
