package com.soen343.shs.converters.collections;

import com.soen343.shs.dal.model.Door;
import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.InteriorDoor;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.ExteriorDoorDTO;
import com.soen343.shs.dto.InteriorDoorDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class ConvertCollectionOfDoors {

    public static Set<DoorDTO> convertDoors(final Set<Door> doors) {
        final Set<DoorDTO> dtos = new HashSet<>();
        doors.forEach(door -> {
            if (door instanceof ExteriorDoor) {
                dtos.add(buildExteriorDoorDTO((ExteriorDoor) door));
            } else {
                dtos.add(buildInteriorDoorDTO(door));
            }
        });
        return dtos;
    }

    public static Set<Door> convertDoorDTOs(final Set<DoorDTO> dtos) {
        final Set<Door> doors = new HashSet<>();
        for (final DoorDTO dto : dtos) {
            if (dto instanceof ExteriorDoorDTO) {
                doors.add(buildExteriorDoor((ExteriorDoorDTO) dto));
            } else {
                doors.add(buildInteriorDoor(dto));
            }
        }
        return doors;
    }

    private static InteriorDoorDTO buildInteriorDoorDTO(final Door door) {
        return InteriorDoorDTO.builder()
                .open(door.getOpen())
                .id(door.getId())
                .build();
    }

    private static ExteriorDoorDTO buildExteriorDoorDTO(final ExteriorDoor door) {
        return ExteriorDoorDTO.builder()
                .locked(door.getLocked())
                .open(door.getOpen())
                .id(door.getId())
                .build();
    }


    private static InteriorDoor buildInteriorDoor(final DoorDTO dto) {
        return InteriorDoor.builder()
                .id(dto.getId())
                .open(dto.getOpen())
                .build();
    }

    private static ExteriorDoor buildExteriorDoor(final ExteriorDoorDTO dto) {

        return ExteriorDoor.builder()
                .locked(dto.getLocked())
                .open(dto.getOpen())
                .id(dto.getId())
                .build();
    }
}
