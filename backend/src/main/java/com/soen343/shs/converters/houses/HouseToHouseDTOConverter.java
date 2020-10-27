package com.soen343.shs.converters.houses;

import com.soen343.shs.converters.rooms.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.City;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dto.CityDTO;
import com.soen343.shs.dto.HouseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class HouseToHouseDTOConverter implements Converter<House, HouseDTO> {
    private ConvertCollectionOfRooms convertCollectionOfRooms;

    @Override
    public HouseDTO convert(final House house) {
        return HouseDTO.builder()
                .id(house.getId())
                .rooms(convertCollectionOfRooms.convertToDTO(house.getRooms()))
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
