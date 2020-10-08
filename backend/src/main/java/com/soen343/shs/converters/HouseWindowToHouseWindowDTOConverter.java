package com.soen343.shs.converters;

import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dto.WindowDTO;
import org.springframework.core.convert.converter.Converter;

public class HouseWindowToHouseWindowDTOConverter implements Converter<HouseWindow, WindowDTO> {
    @Override
    public WindowDTO convert(HouseWindow houseWindow) {
        return WindowDTO.builder()
                .id(houseWindow.getId())
                .blocked(houseWindow.isBlocked())
                .open(houseWindow.isOpen())
                .build();
    }
}
