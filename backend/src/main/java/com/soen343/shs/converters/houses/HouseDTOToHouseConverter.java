package com.soen343.shs.converters.houses;

import com.soen343.shs.converters.collections.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dto.HouseDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.Optional;

public class HouseDTOToHouseConverter implements Converter<HouseDTO, House> {

    @Override
    public House convert(final HouseDTO houseDTO) {
        return House.builder()
                .id(houseDTO.getId())
                .city(houseDTO.getCity())
                .parents(houseDTO.getParents())
                .children(Optional.ofNullable(houseDTO.getChildren()).orElse(Collections.emptySet()))
                .guests(Optional.ofNullable(houseDTO.getGuests()).orElse(Collections.emptySet()))
                .rooms(ConvertCollectionOfRooms.convertRoomDTOs(houseDTO.getRooms()))
                .build();
    }
}

