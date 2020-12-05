package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.Zone;
import com.soen343.shs.dal.model.ZoneState;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.ZoneRepository;
import com.soen343.shs.dal.service.helpers.RoomHelper;
import com.soen343.shs.dto.ZoneDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.HashMap;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SHHServiceTest {

    private final long MOCK_HOUSE_ID = 1;
    private final long MOCK_ZONE_ID = 1;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private ConversionService mvcConversionService;
    @Mock
    private ZoneRepository zoneRepository;
    @InjectMocks
    private SHHService shhService;

    @Test
    public void getZoneTemperatureSuccessfully() {
        when(zoneRepository.findById(MOCK_ZONE_ID)).thenReturn(java.util.Optional.ofNullable(createMockZone()));

        double temperature = shhService.getZoneTemperature(MOCK_ZONE_ID);

        Assertions.assertEquals(temperature, 23);
    }

    @Test
    public void setZoneTemperatureSuccessfully(){
        Zone zone = createMockZone();
        when(zoneRepository.findById(MOCK_ZONE_ID)).thenReturn(java.util.Optional.ofNullable(zone));
        when(mvcConversionService.convert(zoneRepository.save(zone), ZoneDTO.class)).thenReturn(
                ZoneDTO.builder().id(1).rooms(null).temperature(9).build()
        );

        ZoneDTO zoneDTO = shhService.setZoneTemperature(MOCK_ZONE_ID,9);
        Assertions.assertEquals(zoneDTO.getTemperature(), 9);
    }

    private Zone createMockZone() {
        return Zone.builder()
                .rooms(RoomHelper.createRooms())
                .temperature(23)
                .periods(new HashMap<>())
                .zoneState(ZoneState.ZONE)
                .id(1)
                .build();
    }
}
