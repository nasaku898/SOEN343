package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.ExteriorDoorRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dal.repository.LightRepository;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.ExteriorDoorDTO;
import com.soen343.shs.dto.LightDTO;
import com.soen343.shs.dto.WindowDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.HashSet;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {
    private final static long MOCK_ROOM_ID = 1;
    private final static String MOCK_ROOM_NAME = "MockRoom";
    private final static long MOCK_HOUSE_LIGHT_ID = 1;
    private final static long MOCK_HOUSE_WINDOW_ID = 1;
    private final static long MOCK_HOUSE_WINDOW_BLOCKED_ID = 2;
    private final static long MOCK_HOUSE_EXTERIOR_DOOR_ID = 1;
    private final static long MOCK_HOUSE_EXTERIOR_DOOR_LOCKED_ID = 2;

    @Mock
    private LightRepository lightRepository;
    @Mock
    private HouseWindowRepository houseWindowRepository;
    @Mock
    private ExteriorDoorRepository exteriorDoorRepository;
    @Mock
    private ConversionService mvcConversionService;
    @InjectMocks
    private HouseService houseService;

    private static Room buildMockRoom() {
        return Room.builder()
                .id(MOCK_ROOM_ID)
                .name(MOCK_ROOM_NAME)
                .temperature(0)
                .doors(new HashSet<>())
                .lights(new HashSet<>())
                .userIds(new HashSet<>())
                .houseWindows(new HashSet<>())
                .build();
    }

    private static Light buildMockLight(final boolean desiredState) {
        return Light.builder()
                .id(MOCK_HOUSE_LIGHT_ID)
                .isLightOn(desiredState)
                .build();
    }

    private static HouseWindow buildMockHouseWindow(final boolean open, final boolean blocked) {
        return HouseWindow.builder()
                .id(MOCK_HOUSE_WINDOW_ID)
                .open(open)
                .blocked(blocked)
                .build();
    }

    private static ExteriorDoor buildMockExteriorDoor(final boolean open, final boolean locked) {
        return ExteriorDoor.builder()
                .id(MOCK_HOUSE_EXTERIOR_DOOR_ID)
                .open(open)
                .locked(locked)
                .build();
    }

    @Test
    public void turnLightOn() {
        final Light mockLight = buildMockLight(false);
        when(lightRepository.findById(MOCK_HOUSE_LIGHT_ID)).thenReturn(java.util.Optional.ofNullable(mockLight));
        when(mvcConversionService.convert(lightRepository.save(mockLight), LightDTO.class))
                .thenReturn(LightDTO.builder().id(MOCK_HOUSE_LIGHT_ID).isLightOn(true).build());

        final LightDTO lightDTO = houseService.modifyLightState(MOCK_HOUSE_LIGHT_ID, true);

        Assertions.assertEquals(lightDTO.getId(), mockLight.getId());
        Assertions.assertEquals(lightDTO.isLightOn(), mockLight.isLightOn());
    }

    @Test
    public void turnLightOff() {
        final Light mockLight = buildMockLight(true);
        when(lightRepository.findById(MOCK_HOUSE_LIGHT_ID)).thenReturn(java.util.Optional.ofNullable(mockLight));
        when(mvcConversionService.convert(lightRepository.save(mockLight), LightDTO.class))
                .thenReturn(LightDTO.builder().id(MOCK_HOUSE_LIGHT_ID).isLightOn(false).build());

        final LightDTO lightDTO = houseService.modifyLightState(MOCK_HOUSE_LIGHT_ID, false);

        Assertions.assertEquals(lightDTO.getId(), mockLight.getId());
        Assertions.assertEquals(lightDTO.isLightOn(), mockLight.isLightOn());
    }

    @Test
    public void openDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(false, false);
        when(exteriorDoorRepository.findById(MOCK_HOUSE_EXTERIOR_DOOR_ID)).thenReturn(java.util.Optional.ofNullable(mockExteriorDoor));
        when(mvcConversionService.convert(exteriorDoorRepository.save(mockExteriorDoor), DoorDTO.class))
                .thenReturn(ExteriorDoorDTO.builder().id(MOCK_HOUSE_EXTERIOR_DOOR_ID).open(true).locked(false).build());

        final DoorDTO doorDTO = houseService.modifyExteriorDoorState(MOCK_HOUSE_EXTERIOR_DOOR_ID, true, true);

        Assertions.assertEquals(doorDTO.getId(), mockExteriorDoor.getId());
        Assertions.assertEquals(doorDTO.isOpen(), mockExteriorDoor.isOpen());
        Assertions.assertEquals(doorDTO.isLocked(), mockExteriorDoor.isLocked());
    }

    @Test
    public void closeDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(true, false);
        when(exteriorDoorRepository.findById(MOCK_HOUSE_EXTERIOR_DOOR_ID)).thenReturn(java.util.Optional.ofNullable(mockExteriorDoor));
        when(mvcConversionService.convert(exteriorDoorRepository.save(mockExteriorDoor), DoorDTO.class))
                .thenReturn(ExteriorDoorDTO.builder().id(MOCK_HOUSE_EXTERIOR_DOOR_ID).open(false).locked(false).build());

        final DoorDTO doorDTO = houseService.modifyExteriorDoorState(MOCK_HOUSE_EXTERIOR_DOOR_ID, true, false);

        Assertions.assertEquals(doorDTO.getId(), mockExteriorDoor.getId());
        Assertions.assertEquals(doorDTO.isOpen(), mockExteriorDoor.isOpen());
        Assertions.assertEquals(doorDTO.isLocked(), mockExteriorDoor.isLocked());
    }

    @Test
    public void unlockDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(false, true);
        when(exteriorDoorRepository.findById(MOCK_HOUSE_EXTERIOR_DOOR_ID)).thenReturn(java.util.Optional.ofNullable(mockExteriorDoor));
        when(mvcConversionService.convert(exteriorDoorRepository.save(mockExteriorDoor), DoorDTO.class))
                .thenReturn(ExteriorDoorDTO.builder().id(MOCK_HOUSE_EXTERIOR_DOOR_ID).open(false).locked(false).build());

        final DoorDTO doorDTO = houseService.modifyExteriorDoorState(MOCK_HOUSE_EXTERIOR_DOOR_ID, false, false);

        Assertions.assertEquals(doorDTO.getId(), mockExteriorDoor.getId());
        Assertions.assertEquals(doorDTO.isOpen(), mockExteriorDoor.isOpen());
        Assertions.assertEquals(doorDTO.isLocked(), mockExteriorDoor.isLocked());
    }

    // TODO - extra DoorDTO.locked attribute being added to doorDTO object
    @Test
    public void lockDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(false, false);
        when(exteriorDoorRepository.findById(MOCK_HOUSE_EXTERIOR_DOOR_ID)).thenReturn(java.util.Optional.ofNullable(mockExteriorDoor));
        when(mvcConversionService.convert(exteriorDoorRepository.save(mockExteriorDoor), DoorDTO.class))
                .thenReturn(ExteriorDoorDTO.builder().id(MOCK_HOUSE_EXTERIOR_DOOR_ID).open(false).locked(true).build());

        final DoorDTO doorDTO = houseService.modifyExteriorDoorState(MOCK_HOUSE_EXTERIOR_DOOR_ID, false, true);


        Assertions.assertEquals(doorDTO.getId(), mockExteriorDoor.getId());
        Assertions.assertEquals(doorDTO.isOpen(), mockExteriorDoor.isOpen());
        Assertions.assertEquals(doorDTO.isLocked(), mockExteriorDoor.isLocked());
    }

    @Test
    public void openWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(false, false);
        when(houseWindowRepository.findById(MOCK_HOUSE_WINDOW_ID)).thenReturn(java.util.Optional.ofNullable(mockHouseWindow));
        when(mvcConversionService.convert(houseWindowRepository.save(mockHouseWindow), WindowDTO.class))
                .thenReturn(WindowDTO.builder().id(MOCK_HOUSE_WINDOW_ID).open(true).blocked(false).build());

        final WindowDTO windowDTO = houseService.modifyWindowState(MOCK_HOUSE_WINDOW_ID, true, true);

        Assertions.assertEquals(windowDTO.getId(), mockHouseWindow.getId());
        Assertions.assertEquals(windowDTO.isOpen(), mockHouseWindow.isOpen());
        Assertions.assertEquals(windowDTO.isBlocked(), mockHouseWindow.isBlocked());
    }

    @Test
    public void closeWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(true, false);
        when(houseWindowRepository.findById(MOCK_HOUSE_WINDOW_ID)).thenReturn(java.util.Optional.ofNullable(mockHouseWindow));
        when(mvcConversionService.convert(houseWindowRepository.save(mockHouseWindow), WindowDTO.class))
                .thenReturn(WindowDTO.builder().id(MOCK_HOUSE_WINDOW_ID).open(false).blocked(false).build());

        final WindowDTO windowDTO = houseService.modifyWindowState(MOCK_HOUSE_WINDOW_ID, true, false);

        Assertions.assertEquals(windowDTO.getId(), mockHouseWindow.getId());
        Assertions.assertEquals(windowDTO.isOpen(), mockHouseWindow.isOpen());
        Assertions.assertEquals(windowDTO.isBlocked(), mockHouseWindow.isBlocked());
    }

    @Test
    public void unblockWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(false, true);
        when(houseWindowRepository.findById(MOCK_HOUSE_WINDOW_ID)).thenReturn(java.util.Optional.ofNullable(mockHouseWindow));
        when(mvcConversionService.convert(houseWindowRepository.save(mockHouseWindow), WindowDTO.class))
                .thenReturn(WindowDTO.builder().id(MOCK_HOUSE_WINDOW_ID).open(false).blocked(false).build());

        final WindowDTO windowDTO = houseService.modifyWindowState(MOCK_HOUSE_WINDOW_ID, false, false);

        Assertions.assertEquals(windowDTO.getId(), mockHouseWindow.getId());
        Assertions.assertEquals(windowDTO.isOpen(), mockHouseWindow.isOpen());
        Assertions.assertEquals(windowDTO.isBlocked(), mockHouseWindow.isBlocked());
    }

    @Test
    public void blockWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(false, false);
        when(houseWindowRepository.findById(MOCK_HOUSE_WINDOW_ID)).thenReturn(java.util.Optional.ofNullable(mockHouseWindow));
        when(mvcConversionService.convert(houseWindowRepository.save(mockHouseWindow), WindowDTO.class))
                .thenReturn(WindowDTO.builder().id(MOCK_HOUSE_WINDOW_ID).open(false).blocked(true).build());

        final WindowDTO windowDTO = houseService.modifyWindowState(MOCK_HOUSE_WINDOW_ID, false, true);

        Assertions.assertEquals(windowDTO.getId(), mockHouseWindow.getId());
        Assertions.assertEquals(windowDTO.isOpen(), mockHouseWindow.isOpen());
        Assertions.assertEquals(windowDTO.isBlocked(), mockHouseWindow.isBlocked());
    }
}
