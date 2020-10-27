package com.soen343.shs.converters.doors;

import com.soen343.shs.dal.model.Door;
import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.InteriorDoor;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.ExteriorDoorDTO;
import com.soen343.shs.dto.InteriorDoorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class ConvertCollectionOfDoors {
    private final ConversionService mvcConversionService;

    public Set<DoorDTO> convertDoorsToDTO(final Set<Door> doors) {
        final Set<DoorDTO> dtos = new HashSet<>();
        for (final Door door : doors) {
            if (door instanceof ExteriorDoor) {
                dtos.add(mvcConversionService.convert(door, ExteriorDoorDTO.class));
            } else {
                dtos.add(mvcConversionService.convert(door, InteriorDoorDTO.class));
            }
        }
        return dtos;
    }

    public Set<Door> convertDTOsToDoors(final Set<DoorDTO> dtos) {
        final Set<Door> doors = new HashSet<>();
        for (final DoorDTO dto : dtos) {
            if (dto instanceof ExteriorDoorDTO) {
                doors.add(mvcConversionService.convert(dto, ExteriorDoor.class));
            } else {
                doors.add(mvcConversionService.convert(dto, InteriorDoor.class));
            }
        }
        return doors;
    }
}
