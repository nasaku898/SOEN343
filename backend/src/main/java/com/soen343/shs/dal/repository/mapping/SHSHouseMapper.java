package com.soen343.shs.dal.repository.mapping;

import com.soen343.shs.converters.collections.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.City;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.HouseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class SHSHouseMapper {
    private final ConversionService mvcConversionService;

    public House mapHouseDTOToHouse(final HouseDTO dto, final House house) {

        return House.builder()
                .rooms(getRooms(dto, house))
                .city(getCity(dto, house))
                .id(dto.getId())
                .build();
    }

    private City getCity(final HouseDTO dto, final House house) {
        return Objects.isNull(dto.getCity())
                ? house.getCity()
                : mvcConversionService.convert(dto.getCity(), City.class);
    }

    private static Set<Room> getRooms(final HouseDTO dto, final House house) {
        return dto.getRooms().isEmpty()
                ? house.getRooms()
                : ConvertCollectionOfRooms.convertRoomDTOs((dto.getRooms()));
    }
}
