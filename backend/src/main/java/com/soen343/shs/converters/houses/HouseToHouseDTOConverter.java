package com.soen343.shs.converters.houses;

import com.soen343.shs.converters.collections.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.City;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dto.CityDTO;
import com.soen343.shs.dto.HouseDTO;
import org.springframework.core.convert.converter.Converter;

public class HouseToHouseDTOConverter implements Converter<House, HouseDTO> {

    @Override
    public HouseDTO convert(final House house) {
        return HouseDTO.builder()
                .id(house.getId())
                .rooms(ConvertCollectionOfRooms.convertRooms(house.getRooms()))
                .city(getCity(house.getCity()))
                .build();
    }

    private static CityDTO getCity(final City city) {
        return CityDTO.builder()
                .temperatureOutside(city.getTemperatureOutside())
                .name(city.getName())
                .id(city.getId())
                .build();
    }
}
