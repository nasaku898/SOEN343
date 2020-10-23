package com.soen343.shs.converters.houseWindows;

import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dto.WindowDTO;
import org.springframework.core.convert.converter.Converter;

public class HouseWindowToHouseWindowDTOConverter implements Converter<HouseWindow, WindowDTO> {
    @Override
    public WindowDTO convert(final HouseWindow houseWindow) {
        return WindowDTO.builder()
                .id(houseWindow.getId())
                .blocked(houseWindow.getBlocked())
                .open(houseWindow.getOpen())
                .build();
    }
}
