package com.soen343.shs.dal.repository.mapping;

import com.soen343.shs.converters.collections.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.HouseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class SHSHouseMapper {
    public static House mapHouseDTOToHouse(final HouseDTO dto, final House house) {

        return House.builder()
                .rooms(getRooms(dto, house))
                .parents(dto.getParents())
                .children(Optional.ofNullable(dto.getChildren()).orElse(Collections.emptySet()))
                .guests(Optional.ofNullable(dto.getGuests()).orElse(Collections.emptySet()))
                .city(house.getCity())
                .id(dto.getId())
                .build();
    }

    private static Set<Room> getRooms(final HouseDTO dto, final House house) {
        return dto.getRooms().isEmpty()
                ? house.getRooms()
                : ConvertCollectionOfRooms.convertRoomDTOs((dto.getRooms()));
    }
}
