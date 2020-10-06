package com.soen343.shs.converters;

import com.soen343.shs.DTO.HouseDTO;
import com.soen343.shs.dal.model.House;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HouseToHouseDTOConverter implements Converter<House, HouseDTO> {
    @Override
    public HouseDTO convert(House house) {
        return HouseDTO.builder()
                .rooms(house.getRooms())
                .temperatureOutside(house.getTemperatureOutside())
                .build();
    }
}
