package com.soen343.shs.converters;

import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.RoomLayoutDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

public class RoomToRoomLayoutDTOConverter implements Converter<Room, RoomLayoutDTO> {
    @Override
    public RoomLayoutDTO convert(Room room) {
        return RoomLayoutDTO.builder()
                .nbOfLights(room.getLights().size())
                .nbOfWindows(room.getHouseWindows().size())
                .build();
    }

    public Set<RoomLayoutDTO> convert(Set<Room> rooms){
        Set<RoomLayoutDTO> roomLayoutDTOS = new HashSet<RoomLayoutDTO>();
        for (Room room: rooms){
            RoomLayoutDTO roomLayoutDTO = convert(room);
            roomLayoutDTOS.add(roomLayoutDTO);
        }

        return  roomLayoutDTOS;
    }
}
