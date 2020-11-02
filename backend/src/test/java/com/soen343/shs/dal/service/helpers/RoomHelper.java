package com.soen343.shs.dal.service.helpers;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.ExteriorDoorDTO;
import com.soen343.shs.dto.LightDTO;
import com.soen343.shs.dto.RoomDTO;
import com.soen343.shs.dto.WindowDTO;

import java.util.Collections;
import java.util.Set;

public class RoomHelper {

    public static final String ROOM_NAME = "someName";
    public static final long ROOM_ID = 1;

    public static Set<Room> createRooms() {
        return Collections
                .singleton(Room.builder()
                        .id(ROOM_ID)
                        .userIds(Collections.emptySet())
                        .doors(Collections.singleton(ExteriorDoor.builder().build()))
                        .houseWindows(Collections.singleton(HouseWindow.builder().build()))
                        .lights(Collections.singleton(Light.builder().build()))
                        .name(ROOM_NAME)
                        .build());
    }

    public static Set<RoomDTO> createRoomDTO() {
        return Collections
                .singleton(RoomDTO.builder()
                        .roomId(ROOM_ID)
                        .doors(Collections.singleton(ExteriorDoorDTO.builder().build()))
                        .windows(Collections.singleton(WindowDTO.builder().build()))
                        .lights(Collections.singleton(LightDTO.builder().build()))
                        .name(ROOM_NAME)
                        .build());
    }
}
