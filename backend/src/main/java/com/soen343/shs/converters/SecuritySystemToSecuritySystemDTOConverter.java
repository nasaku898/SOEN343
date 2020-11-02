package com.soen343.shs.converters;

import com.soen343.shs.converters.collections.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.SecuritySystem;
import com.soen343.shs.dto.SecuritySystemDTO;
import org.springframework.core.convert.converter.Converter;

public class SecuritySystemToSecuritySystemDTOConverter implements Converter<SecuritySystem, SecuritySystemDTO> {

    @Override
    public SecuritySystemDTO convert(final SecuritySystem entity) {
        return SecuritySystemDTO.builder()
                .auto(entity.getAuto())
                .away(entity.getAway())
                .houseId(entity.getHouseId())
                .rooms(ConvertCollectionOfRooms.convertRooms(entity.getRooms()))
                .id(entity.getId())
                .build();
    }
}
