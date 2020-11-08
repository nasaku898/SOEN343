package com.soen343.shs.converters.securitySystem;

import com.soen343.shs.dal.model.AwayMode;
import com.soen343.shs.dal.model.SecuritySystem;
import com.soen343.shs.dto.AwayModeDTO;
import com.soen343.shs.dto.SecuritySystemDTO;
import org.springframework.core.convert.converter.Converter;

public class SecuritySystemToSecuritySystemDTOConverter implements Converter<SecuritySystem, SecuritySystemDTO> {

    @Override
    public SecuritySystemDTO convert(final SecuritySystem securitySystem) {
        return SecuritySystemDTO.builder()
                .auto(securitySystem.getAuto())
                .awayMode(convertAwayMode(securitySystem.getAwayMode()))
                .houseId(securitySystem.getHouseId())
                .id(securitySystem.getId())
                .build();
    }

    private static AwayModeDTO convertAwayMode(final AwayMode awayMode) {
        return AwayModeDTO.builder()
                .active(awayMode.getActive())
                .intruderDetectionDelay(awayMode.getIntruderDetectionDelay())
                .id(awayMode.getId())
                .build();
    }
}
