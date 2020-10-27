package com.soen343.shs.converters.rooms;

import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ConvertCollectionOfRooms {
    private final ConversionService mvcConversionService;

    public Set<Room> convertToEntity(final Set<RoomDTO> rooms) {
        return rooms.stream()
                .map(room -> mvcConversionService.convert(room, Room.class))
                .collect(Collectors.toSet());
    }

    public Set<RoomDTO> convertToDTO(final Set<Room> rooms) {
        return rooms.stream()
                .map(room -> mvcConversionService.convert(room, RoomDTO.class))
                .collect(Collectors.toSet());
    }
}
