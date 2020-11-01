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
    private CityService cityService;

    @Mock
    private ConversionService mvcConversionService;

    @InjectMocks
    private LoadSimulationService classUnderTest;

    public static final String ROOM_NAME = "someName";
    private static final long ROOM_ID = 1;
    private static final String CITY_NAME = "Montreal";

    @Test
    void testLoadHouse() {
        final House house = House.builder().id(1L).city("Montreal").parents(Collections.singleton(1L)).rooms(Collections.singleton(Room.builder().build())).build();
        final RealUserDTO user = Mockito.mock(RealUserDTO.class);
        final LoadHouseDTO mockHouse = createLoadHouse();

        when(userService.getUserByUsername(USERNAME, RealUserDTO.class)).thenReturn(user);

        when(cityService.getCity(mockHouse.getCity())).thenReturn(CityDTO.builder().build());
        when(houseRepository.save(any(House.class))).thenReturn(house);

        when(mvcConversionService.convert(any(LoadInteriorDoorDTO.class), any())).thenReturn(InteriorDoor.builder().build());
        when(mvcConversionService.convert(any(LoadHouseWindowDTO.class), any())).thenReturn(HouseWindow.builder().build());
        when(mvcConversionService.convert(any(LoadLightDTO.class), any())).thenReturn(Light.builder().build());
        when(mvcConversionService.convert(house, HouseDTO.class)).thenReturn(createHouseDTO());

        final HouseDTO dto = classUnderTest.loadHouse(mockHouse, USERNAME);
        Assertions.assertEquals(house.getId(), dto.getId());
        Assertions.assertEquals(house.getParents(), dto.getParents());
        Assertions.assertEquals(house.getRooms().size(), dto.getRooms().size());
    }


    private static LoadHouseDTO createLoadHouse() {
        return LoadHouseDTO.builder()
                .city(CITY_NAME)
                .rooms(createLoadRooms())
                .build();
    }

    private static HouseDTO createHouseDTO() {
        return HouseDTO.builder()
                .rooms(createRoomDTO())
                .city(CITY_NAME)
                .parents(Collections.singleton(1L))
                .children(Collections.emptySet())
                .guests(Collections.emptySet())
                .id(1L)
                .build();
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

    private static Set<RoomDTO> createRoomDTO() {
        return Collections
                .singleton(RoomDTO.builder()
                        .roomId(ROOM_ID)
                        .doors(Collections.singleton(ExteriorDoorDTO.builder().build()))
                        .windows(Collections.singleton(WindowDTO.builder().build()))
                        .lights(Collections.singleton(LightDTO.builder().build()))
                        .name(ROOM_NAME)
                        .build());
    }
}
