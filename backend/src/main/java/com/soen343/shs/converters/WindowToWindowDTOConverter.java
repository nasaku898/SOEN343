package com.soen343.shs.converters;

import org.springframework.core.convert.converter.Converter;
import com.soen343.shs.dal.model.Window;
import com.soen343.shs.dto.WindowDTO;

public class WindowToWindowDTOConverter implements Converter<Window, WindowDTO> {

    @Override
    public WindowDTO convert(Window window) {
        return WindowDTO.builder().id(window.getId()).blocked(window.isBlocked()).open(window.isOpen()).build();
    }
}
