package com.soen343.shs.converters.houses;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.HouseDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class HouseToHouseDTOConverter implements Converter<House, HouseDTO> {
    @Override
    public HouseDTO convert(final House house) {
        return HouseDTO.builder()
                .id(house.getId())
                .rooms(house.getRooms()
                        .stream()
                        .collect(Collectors.toMap(Room::getId, Room::getName)))
                .city(house.getCity().getName())
                .temperatureOutside(house.getCity().getTemperatureOutside())
                .build();
    }
}
