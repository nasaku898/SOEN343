package com.soen343.shs.converters.rooms;

import com.soen343.shs.converters.doors.ConvertCollectionOfDoors;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.LightDTO;
import com.soen343.shs.dto.RoomDTO;
import com.soen343.shs.dto.WindowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RoomDTOToRoomConverter implements Converter<Room, RoomDTO> {
    private final ConversionService mvcConversionService;
    private final ConvertCollectionOfDoors convertCollectionOfDoors;

    @Override
    public RoomDTO convert(final Room room) {
        return RoomDTO.builder()
                .doors(convertCollectionOfDoors.convertDoorsToDTO(room.getDoors()))
                .lights(convertLights(room.getLights()))
                .windows(convertWindows(room.getHouseWindows()))
                .build();
    }


    private Set<WindowDTO> convertWindows(final Set<HouseWindow> windows) {
        return windows.stream()
                .map(window -> mvcConversionService.convert(window, WindowDTO.class))
                .collect(Collectors.toSet());
    }

    private Set<LightDTO> convertLights(final Set<Light> lights) {
        return lights
                .stream()
                .map(light -> mvcConversionService.convert(light, LightDTO.class))
                .collect(Collectors.toSet());
    }
}
