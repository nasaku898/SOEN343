package com.soen343.shs.converters;

import com.soen343.shs.dto.RoomDTO;
import com.soen343.shs.dal.model.Room;
import org.springframework.core.convert.converter.Converter;

public class RoomToRoomDTOConverter implements Converter<Room, RoomDTO> {
    @Override
    public RoomDTO convert(Room room){
        return RoomDTO.builder()
                .doors(room.getDoors())
                .lights(room.getLights())
                .name(room.getName())
                .temperature(room.getTemperature())
                .windows(room.getWindows())
                .userIds(room.getUserIds())
                .build();
    }
}
