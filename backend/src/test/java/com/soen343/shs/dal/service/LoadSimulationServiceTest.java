package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.Door;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.Collections;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoadSimulationServiceTest {

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private ConversionService mvcConversionService;

    @InjectMocks
    private LoadSimulationService classUnderTest;

    private static final String ROOM_NAME = "someName";

    @Test
    void testLoadHouse() {
        final House house = House.builder().rooms(createRooms()).build();

        when(mvcConversionService.convert(any(LoadDoorDTO.class), any())).thenReturn(DoorDTO.builder().build());
        when(mvcConversionService.convert(any(LoadHouseWindowDTO.class), any())).thenReturn(WindowDTO.builder().build());
        when(mvcConversionService.convert(any(LoadLightDTO.class), any())).thenReturn(LightDTO.builder().build());
        when(houseRepository.save(any(House.class))).thenReturn(house);
        when(mvcConversionService.convert(eq(house), eq(HouseDTO.class))).thenReturn(HouseDTO.builder().build());

        final HouseDTO dto = classUnderTest.loadHouse(LoadHouseDTO
                .builder()
                .rooms(createLoadRooms())
                .build());

        Assertions.assertNotNull(dto);
    }

    private static Set<LoadRoomDTO> createLoadRooms() {
        return Collections
                .singleton(LoadRoomDTO.builder()
                        .doors(Collections.singleton(LoadDoorDTO
                                .builder()
                                .build())
                        )
                        .houseWindows(Collections.singleton(LoadHouseWindowDTO.builder().build()))
                        .lights(Collections.singleton(LoadLightDTO.builder().build()))
                        .name(ROOM_NAME)
                        .build());
    }

    private static Set<Room> createRooms() {
        return Collections
                .singleton(Room.builder()
                        .doors(Collections.singleton(Door
                                .builder()
                                .build())
                        )
                        .houseWindows(Collections.singleton(HouseWindow.builder().build()))
                        .lights(Collections.singleton(Light.builder().build()))
                        .name(ROOM_NAME)
                        .build());
    }
}
