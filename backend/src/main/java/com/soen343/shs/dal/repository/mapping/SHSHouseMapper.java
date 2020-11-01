package com.soen343.shs.dal.repository.mapping;

import com.soen343.shs.converters.collections.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.HouseDTO;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SHSHouseMapper {
    public static House mapHouseDTOToHouse(final HouseDTO dto, final House house) {
        house.setRooms(getRooms(dto, house));
        house.setParents(dto.getParents());
        house.setChildren(Optional.ofNullable(dto.getChildren()).orElse(Collections.emptySet()));
        house.setGuests(Optional.ofNullable(dto.getGuests()).orElse(Collections.emptySet()));
        return house;
    }

    private static Set<Room> getRooms(final HouseDTO dto, final House house) {
        final Set<RoomDTO> dtos = dto.getRooms();
        final Set<Room> rooms = house.getRooms();

        return dtos.isEmpty() || compareRooms(dtos, rooms)
                ? rooms
                : mergeRooms(rooms, dtos);
    }

    private static boolean compareRooms(final Set<RoomDTO> rooms2, final Set<Room> rooms) {
        final List<Long> entityIds = rooms.stream().map(Room::getId).collect(Collectors.toList());
        final List<Long> dtoIds = rooms2.stream().map((RoomDTO::getRoomId)).collect(Collectors.toList());
        return dtoIds.containsAll(entityIds);
    }

    private static Set<Room> mergeRooms(final Set<Room> rooms, final Set<RoomDTO> dtos) {
        ConvertCollectionOfRooms.convertRoomDTOs(dtos)
                .stream()
                .filter(room -> !rooms.contains(room))
                .forEach(rooms::add);
        return rooms;
    }
}
