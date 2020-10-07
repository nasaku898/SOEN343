package com.soen343.shs.converters;

import com.soen343.shs.dal.model.Door;
import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.model.Window;
import com.soen343.shs.dto.RoomDTO;
import com.soen343.shs.dal.model.Room;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

public class RoomToRoomDTOConverter implements Converter<Room, RoomDTO> {

    @Override
    public RoomDTO convert(Room room) {
        return RoomDTO.builder()
                .doorIds(room.getDoors().stream().map(Door::getId).collect(Collectors.toSet()))
                .lightIds(room.getLights().stream().map(Light::getId).collect(Collectors.toSet()))
                .name(room.getName())
                .temperature(room.getTemperature())
                .windowIds(room.getWindows().stream().map(Window::getId).collect(Collectors.toSet()))
                .userIds(room.getUserIds())
                .build();
    }
}
