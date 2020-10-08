package com.soen343.shs.converters;

import com.soen343.shs.DTO.HouseDTO;
import com.soen343.shs.dal.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class HouseToHouseDTOConverter implements Converter<House, HouseDTO> {
    @Autowired
    private RoomToRoomDTOConverter roomToRoomDTOConverter;

    @Override
    public HouseDTO convert(House house) {
        return HouseDTO.builder()
                .id(house.getId())
                .rooms(house.getRooms().stream().map(room -> roomToRoomDTOConverter.convert(room)).collect(Collectors.toSet()))
                .temperatureOutside(house.getTemperatureOutside())
                .build();
    }
}
