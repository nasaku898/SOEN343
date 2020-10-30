package com.soen343.shs.converters.houses;

import com.soen343.shs.converters.collections.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dto.HouseDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.Optional;

public class HouseToHouseDTOConverter implements Converter<House, HouseDTO> {

    @Override
    public HouseDTO convert(final House house) {
        return HouseDTO.builder()
                .id(house.getId())
                .rooms(ConvertCollectionOfRooms.convertRooms(house.getRooms()))
                .parents(house.getParents())
                .children(Optional.ofNullable(house.getChildren()).orElse(Collections.emptySet()))
                .guests(Optional.ofNullable(house.getGuests()).orElse(Collections.emptySet()))
                .city(house.getCity())
                .build();
    }
}
