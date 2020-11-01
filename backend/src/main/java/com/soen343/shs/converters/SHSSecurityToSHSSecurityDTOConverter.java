package com.soen343.shs.converters;

import com.soen343.shs.converters.collections.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.SHSSecurity;
import com.soen343.shs.dto.SHSSecurityDTO;
import org.springframework.core.convert.converter.Converter;

public class SHSSecurityToSHSSecurityDTOConverter implements Converter<SHSSecurity, SHSSecurityDTO> {

    @Override
    public SHSSecurityDTO convert(final SHSSecurity entity) {
        return SHSSecurityDTO.builder()
                .auto(entity.getAuto())
                .away(entity.getAway())
                .houseId(entity.getHouseId())
                .rooms(ConvertCollectionOfRooms.convertRooms(entity.getRooms()))
                .id(entity.getId())
                .build();
    }
}
