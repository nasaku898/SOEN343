package com.soen343.shs.converters;

import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dto.LoadHouseWindowDTO;
import org.springframework.core.convert.converter.Converter;

public class LoadHouseWindowDTOToHouseWindowConverter implements Converter<LoadHouseWindowDTO, HouseWindow> {

    @Override
    public HouseWindow convert(LoadHouseWindowDTO loadHouseWindowDTO) {
        return HouseWindow.builder()
                .blocked(loadHouseWindowDTO.isBlocked())
                .open(loadHouseWindowDTO.isOpen())
                .build();
    }
}
