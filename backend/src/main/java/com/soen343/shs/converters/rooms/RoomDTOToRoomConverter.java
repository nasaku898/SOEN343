package com.soen343.shs.converters.rooms;

import com.soen343.shs.converters.collections.ConvertCollectionOfDoors;
import com.soen343.shs.converters.collections.ConvertCollectionOfLights;
import com.soen343.shs.converters.collections.ConvertCollectionOfWindows;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.RoomDTO;
import org.springframework.core.convert.converter.Converter;

public class RoomDTOToRoomConverter implements Converter<Room, RoomDTO> {

    @Override
    public RoomDTO convert(final Room room) {
        return RoomDTO.builder()
                .doors(ConvertCollectionOfDoors.convertDoors(room.getDoors()))
                .lights(ConvertCollectionOfLights.convertLights(room.getLights()))
                .windows(ConvertCollectionOfWindows.convertWindows(room.getHouseWindows()))
                .build();
    }

}
