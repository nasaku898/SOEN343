package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.*;
import com.soen343.shs.dal.repository.HouseMemberRepository;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dto.HouseMemberDTO;
import com.soen343.shs.dto.WindowDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.HashSet;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimulationServiceTest {

    private final static long MOCK_ROOM_ID = 1;
    private final static long MOCK_HOUSE_MEMBER_ID = 1;
    private final static String MOCK_HOUSE_MEMBER_NAME = "John";
    private final static long MOCK_HOUSE_WINDOW_ID = 1;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private HouseMemberRepository houseMemberRepository;
    @Mock
    private HouseWindowRepository houseWindowRepository;
    @Mock
    private ConversionService mvcConversionService;
    @Mock
    private ModelFetchHandler modelFetchHandler;
    @InjectMocks
    private SimulationService simulationService;

    private static HouseMember buildMockHouseMember() {
        return HouseMember.builder()
                .id(MOCK_HOUSE_MEMBER_ID)
                .name(MOCK_HOUSE_MEMBER_NAME)
                .location(null)
                .role(UserRole.PARENT)
                .build();
    }

    private static Room buildMockRoom() {
        return Room.builder()
                .id(MOCK_ROOM_ID)
                .name("MockRoom")
                .temperature(0)
                .doors(new HashSet<Door>())
                .lights(new HashSet<Light>())
                .userIds(new HashSet<Long>())
                .houseWindows(new HashSet<HouseWindow>())
                .build();
    }

    private static HouseWindow buildMockHouseWindow() {
        return HouseWindow.builder()
                .id(MOCK_HOUSE_WINDOW_ID)
                .open(true)
                .blocked(false)
                .build();
    }

    @Test
    public void moveUserToRoomSuccessfully() {
        Room mockRoom = buildMockRoom();
        HouseMember mockHouseMember = buildMockHouseMember();
        when(modelFetchHandler.findRoom(MOCK_ROOM_ID)).thenReturn(mockRoom);
        when(houseMemberRepository.findByName(MOCK_HOUSE_MEMBER_NAME)).thenReturn(mockHouseMember);

        when(mvcConversionService.convert(houseMemberRepository.save(mockHouseMember), HouseMemberDTO.class))
                .thenReturn(HouseMemberDTO.builder().roomId(MOCK_ROOM_ID).build());

        HouseMemberDTO houseMemberDTO = simulationService.moveUserToRoom(MOCK_HOUSE_MEMBER_NAME, MOCK_ROOM_ID);

        Assertions.assertEquals(houseMemberDTO.getRoomId(), MOCK_ROOM_ID);
    }

    @Test
    public void blockWindowSuccessfully() {
        HouseWindow mockHouseWindow = buildMockHouseWindow();
        when(houseWindowRepository.findById(MOCK_HOUSE_WINDOW_ID)).thenReturn(java.util.Optional.ofNullable(mockHouseWindow));
        when(mvcConversionService.convert(houseWindowRepository.save(mockHouseWindow), WindowDTO.class))
                .thenReturn(WindowDTO.builder().blocked(true).build());

        WindowDTO windowDTO = simulationService.addObjectToWindow(MOCK_HOUSE_WINDOW_ID);

        Assertions.assertEquals(windowDTO.isBlocked(), mockHouseWindow.isBlocked());
    }
}
