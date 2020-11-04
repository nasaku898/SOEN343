package com.soen343.shs.converters.securitySystem;

import com.soen343.shs.dal.model.SecuritySystem;
import com.soen343.shs.dto.SecuritySystemDTO;
import org.springframework.core.convert.converter.Converter;

public class SecuritySystemToSecuritySystemDTOConverter implements Converter<SecuritySystem, SecuritySystemDTO> {
//    private final ConvertCollectionOfSensors convertCollectionOfSensors = new ConvertCollectionOfSensors();

    @Override
    public SecuritySystemDTO convert(final SecuritySystem entity) {
        return SecuritySystemDTO.builder()
                .auto(entity.getAuto())
                .away(entity.getAway())
                .houseId(entity.getHouseId())
//                .sensors(C)
                .id(entity.getId())
                .build();
    }
}
