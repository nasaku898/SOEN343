package com.soen343.shs.converters.zones;

import com.soen343.shs.converters.collections.ConvertCollectionOfRooms;
import com.soen343.shs.dal.model.Zone;
import com.soen343.shs.dto.ZoneDTO;
import org.springframework.core.convert.converter.Converter;

public class ZoneToZoneDTO implements Converter<Zone, ZoneDTO> {
    @Override
    public ZoneDTO convert(Zone zone) {
        return ZoneDTO.builder()
                .id(zone.getId())
                .rooms(ConvertCollectionOfRooms.convertRooms(zone.getRooms()))
                .temperature(zone.getTemperature())
                .zoneState(zone.getZoneState())
                .build();
    }
}
