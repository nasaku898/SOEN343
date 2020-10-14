package com.soen343.shs.converters;

import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dto.LoadLightDTO;
import org.springframework.core.convert.converter.Converter;


public class LoadLightDTOToLightConverter implements Converter<LoadLightDTO, Light> {

    @Override
    public Light convert(LoadLightDTO loadLightDTO) {
        return Light.builder()
                .isLightOn(loadLightDTO.isLightOn())
                .build();
    }
}
