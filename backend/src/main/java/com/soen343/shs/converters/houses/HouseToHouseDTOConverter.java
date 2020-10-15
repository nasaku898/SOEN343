package com.soen343.shs.converters.houses;

import com.soen343.shs.DTO.HouseDTO;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import org.springframework.core.convert.converter.Converter;
import java.util.stream.Collectors;

public class HouseToHouseDTOConverter implements Converter<House, HouseDTO> {
    @Override
    public HouseDTO convert(House house) {
        return HouseDTO.builder()
                .id(house.getId())
                .roomIds(house.getRooms().stream().map(Room::getId).collect(Collectors.toSet()))
                .temperatureOutside(house.getTemperatureOutside())
                .build();
    }
}
