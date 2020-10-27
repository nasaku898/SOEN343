package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.City;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dto.RoomDTO;
import com.soen343.shs.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static com.soen343.shs.dal.service.helpers.HouseMemberHelper.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimulationServiceTest {

    @Mock
    private HouseService houseService;

    @Mock
    private UserService userService;

    @Mock
    private ConversionService mvcConversionService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private SimulationService classUnderTest;


    @Test
    public void moveUserToRoomSuccessfully() {
        final UserDTO dto = Mockito.mock(UserDTO.class);

        when(userService.getUserByUsername(MOCK_HOUSE_MEMBER_NAME, UserDTO.class)).thenReturn(dto);
        when(roomService.getRoom(MOCK_ROOM_ID)).thenReturn(buildRoomDTO());

        when(dto.getRoomId()).thenReturn(new HashMap<>());

        when(userService.updateUser(dto.getId(), dto)).thenReturn(dto);

        final UserDTO houseMemberDTO = classUnderTest.moveUserToRoom(MOCK_HOUSE_MEMBER_NAME, MOCK_ROOM_ID, UserDTO.class);

        Assertions.assertEquals(MOCK_ROOM_ID, houseMemberDTO.getRoomId().keySet().iterator().next());
    }

    @Test
    public void testFindAllRooms() {
        final House house = House.builder()
                .city(City.builder()
                        .build())
                .rooms(Collections.singleton(buildMockRoom()))
                .id(1L)
                .build();

        final Set<RoomDTO> dto = Collections.singleton(buildRoomDTO());

        when(houseService.fetchHouse(anyLong())).thenReturn(house);
        when(mvcConversionService.convert(any(Room.class), any())).thenReturn(dto);
        final Set<RoomDTO> rooms = classUnderTest.findAllRooms(house.getId());
        Assertions.assertEquals(dto, rooms);
    }

    private static RoomDTO buildRoomDTO() {
        return RoomDTO.builder()
                .roomId(MOCK_ROOM_ID)
                .name("MockRoom")
                .temperature(0)
                .userIds(Collections.emptySet())
                .windows(Collections.emptySet())
                .doors(Collections.emptySet())
                .lights(Collections.emptySet())
                .build();
    }


    private static Room buildMockRoom() {
        return Room.builder()
                .id(MOCK_ROOM_ID)
                .name("MockRoom")
                .temperature(0)
                .doors(new HashSet<>())
                .lights(new HashSet<>())
                .userIds(new HashSet<>())
                .houseWindows(new HashSet<>())
                .build();
    }

    private static HouseWindow buildMockHouseWindow() {
        return HouseWindow.builder()
                .id(MOCK_HOUSE_WINDOW_ID)
                .open(true)
                .blocked(false)
                .build();
    }
}
