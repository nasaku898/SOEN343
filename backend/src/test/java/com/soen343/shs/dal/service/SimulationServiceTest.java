package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.HouseMemberRepository;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dal.repository.UserRepository;
import com.soen343.shs.dto.HouseMemberDTO;
import com.soen343.shs.dto.RoomDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static com.soen343.shs.dal.service.helpers.HouseMemberHelper.MOCK_HOUSE_MEMBER_NAME;
import static com.soen343.shs.dal.service.helpers.HouseMemberHelper.MOCK_HOUSE_WINDOW_ID;
import static com.soen343.shs.dal.service.helpers.HouseMemberHelper.MOCK_ROOM_ID;
import static com.soen343.shs.dal.service.helpers.HouseMemberHelper.MOCK_ROOM_NAME;
import static com.soen343.shs.dal.service.helpers.HouseMemberHelper.buildMockHouseMember;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimulationServiceTest {

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private HouseMemberRepository houseMemberRepository;

    @Mock
    private HouseWindowRepository houseWindowRepository;

    @Mock
    private ConversionService mvcConversionService;

    @Mock
    private RoomService roomService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SimulationService classUnderTest;


    @Test
    public void moveUserToRoomSuccessfully() {
        final Room mockRoom = buildMockRoom();
        final HouseMember mockHouseMember = buildMockHouseMember();
        when(roomService.fetchRoom(MOCK_ROOM_ID)).thenReturn(mockRoom);
        when(userRepository.findByUsername(HouseMember.class, MOCK_HOUSE_MEMBER_NAME)).thenReturn(ofNullable(mockHouseMember));

        when(mvcConversionService.convert(houseMemberRepository.save(mockHouseMember), HouseMemberDTO.class))
                .thenReturn(HouseMemberDTO.builder()
                        .username(MOCK_HOUSE_MEMBER_NAME)
                        .roomId(Collections.singletonMap(MOCK_ROOM_ID, MOCK_ROOM_NAME))
                        .build());

        final HouseMemberDTO houseMemberDTO = classUnderTest.moveUserToRoom(MOCK_HOUSE_MEMBER_NAME, MOCK_ROOM_ID, HouseMember.class, HouseMemberDTO.class);

        Assertions.assertEquals(MOCK_ROOM_ID, houseMemberDTO.getRoomId().keySet().iterator().next());
    }

    @Test
    public void testFindAllRooms() {
        final House house = House.builder().rooms(Collections.singleton(buildMockRoom())).build();
        final RoomDTO dto = buildRoomDTO();
        when(houseRepository.findById(house.getId())).thenReturn(of(house));
        when(mvcConversionService.convert(any(Room.class), any())).thenReturn(dto);
        final List<RoomDTO> rooms = (classUnderTest.findAllRooms(house.getId()));
        Assertions.assertEquals(dto, rooms.get(0));
    }

    private static RoomDTO buildRoomDTO() {
        return RoomDTO.builder()
                .roomId(MOCK_ROOM_ID)
                .name("MockRoom")
                .temperature(0)
                .userIds(Collections.emptySet())
                .windowIds(Collections.emptySet())
                .doorIds(Collections.emptySet())
                .lightIds(Collections.emptySet())
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
