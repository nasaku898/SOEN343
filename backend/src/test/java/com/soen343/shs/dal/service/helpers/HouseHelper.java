package com.soen343.shs.dal.service.helpers;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dto.HouseDTO;

import java.util.Collections;

import static com.soen343.shs.dal.service.helpers.RoomHelper.createRoomDTO;
import static com.soen343.shs.dal.service.helpers.RoomHelper.createRooms;

public class HouseHelper {
    public static final String CITY_NAME = "Montreal";
    public static final long HOUSE_ID = 1L;

    public static House buildHouse() {
        return House.builder()
                .id(HOUSE_ID)
                .city(CITY_NAME)
                .parents(Collections.singleton(1L))
                .rooms(createRooms())
                .build();
    }

    public static HouseDTO createHouseDTO() {
        return HouseDTO.builder()
                .rooms(createRoomDTO())
                .city(CITY_NAME)
                .parents(Collections.singleton(1L))
                .children(Collections.emptySet())
                .guests(Collections.emptySet())
                .id(1L)
                .build();
    }

}
