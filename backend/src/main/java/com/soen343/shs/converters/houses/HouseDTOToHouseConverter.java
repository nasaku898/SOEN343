package com.soen343.shs.converters.houses;

import com.soen343.shs.converters.rooms.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.City;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dto.CityDTO;
import com.soen343.shs.dto.HouseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class HouseDTOToHouseConverter implements Converter<HouseDTO, House> {
    private final ConvertCollectionOfRooms convertCollectionOfRooms;

    @Override
    public House convert(final HouseDTO houseDTO) {
        return House.builder()
                .id(houseDTO.getId())
                .city(convertCity(houseDTO.getCity()))
                .rooms(convertCollectionOfRooms.convertToEntity(houseDTO.getRooms()))
                .build();
    }

    private static City convertCity(final CityDTO city) {
        return City.builder()
                .id(city.getId())
                .name(city.getName())
                .temperatureOutside(city.getTemperatureOutside())
                .build();
    }

}

