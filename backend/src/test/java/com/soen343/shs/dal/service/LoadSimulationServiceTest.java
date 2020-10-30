package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.*;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.Collections;
import java.util.Set;

import static com.soen343.shs.dal.service.helpers.UserTestHelper.USERNAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoadSimulationServiceTest {

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private UserService userService;

    @Mock
    private ConversionService mvcConversionService;

    @InjectMocks
    private LoadSimulationService classUnderTest;

    public static final String ROOM_NAME = "someName";
    private static final long ROOM_ID = 1;

    @Test
    void testLoadHouse() {
        final House house = House.builder().id(1L).rooms(createRooms()).build();
        final RealUserDTO user = Mockito.mock(RealUserDTO.class);

        when(userService.getUserByUsername(USERNAME, RealUserDTO.class)).thenReturn(user);
        when(mvcConversionService.convert(any(LoadDoorDTO.class), any())).thenReturn(DoorDTO.builder().build());
        when(mvcConversionService.convert(any(LoadHouseWindowDTO.class), any())).thenReturn(WindowDTO.builder().build());
        when(mvcConversionService.convert(any(LoadLightDTO.class), any())).thenReturn(LightDTO.builder().build());
        when(user.getHouseIds()).thenReturn(Collections.emptySet());

        final HouseDTO dto = classUnderTest.loadHouse(LoadHouseDTO
                .builder()
                .rooms(createLoadRooms())
                .build(), USERNAME);

        Assertions.assertNotNull(dto);
    }

    private static Set<LoadRoomDTO> createLoadRooms() {
        return Collections
                .singleton(LoadRoomDTO.builder()
                        .doors(Collections.singleton(LoadInteriorDoorDTO
                                .builder()
                                .build()))
                        .houseWindows(Collections.singleton(LoadHouseWindowDTO.builder().build()))
                        .lights(Collections.singleton(LoadLightDTO.builder().build()))
                        .name(ROOM_NAME)
                        .build());
    }

    private static Set<Room> createRooms() {
        return Collections
                .singleton(Room.builder()
                        .id(ROOM_ID)
                        .doors(Collections.singleton(ExteriorDoor.builder().build()))
                        .houseWindows(Collections.singleton(HouseWindow.builder().build()))
                        .lights(Collections.singleton(Light.builder().build()))
                        .name(ROOM_NAME)
                        .build());
    }
}
