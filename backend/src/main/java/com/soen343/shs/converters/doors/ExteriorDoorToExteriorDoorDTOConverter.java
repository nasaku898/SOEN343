package com.soen343.shs.converters.doors;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dto.ExteriorDoorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class ExteriorDoorToExteriorDoorDTOConverter implements Converter<ExteriorDoor, ExteriorDoorDTO> {
    private final ConversionService mvcConversionService;

    @Override
    public ExteriorDoorDTO convert(final ExteriorDoor door) {
        return ExteriorDoorDTO.builder()
                .locked(door.getLocked())
                .id(door.getId())
                .open(door.getOpen())
                .build();
    }
}
