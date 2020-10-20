package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.repository.ExteriorDoorRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dal.repository.LightRepository;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.LightDTO;
import com.soen343.shs.dto.WindowDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {
    private final static long MOCK_HOUSE_LIGHT_ID = 1;
    private final static long MOCK_HOUSE_WINDOW_ID = 1;
    private final static long MOCK_HOUSE_EXTERIOR_DOOR_ID = 1;

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

    @Test
    public void turnLightOn() {
        final Light mockLight = buildMockLight(false);
        final LightDTO lightDTO = setUpLight(mockLight, true);

        Assertions.assertEquals(lightDTO.getId(), mockLight.getId());
        Assertions.assertEquals(lightDTO.isLightOn(), mockLight.isLightOn());
    }

    @Test
    public void turnLightOff() {
        final Light mockLight = buildMockLight(true);
        final LightDTO lightDTO = setUpLight(mockLight, false);

        Assertions.assertEquals(lightDTO.getId(), mockLight.getId());
        Assertions.assertEquals(lightDTO.isLightOn(), mockLight.isLightOn());
    }

    @Test
    public void openDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(false, false);
        final DoorDTO doorDTO = setUpExteriorDoor(mockExteriorDoor, true, true, false);

        Assertions.assertEquals(doorDTO.getId(), mockExteriorDoor.getId());
        Assertions.assertEquals(doorDTO.isOpen(), mockExteriorDoor.isOpen());
        Assertions.assertEquals(doorDTO.isLocked(), mockExteriorDoor.isLocked());
    }

    @Test
    public void closeDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(true, false);
        final DoorDTO doorDTO = setUpExteriorDoor(mockExteriorDoor, true, false, false);

        Assertions.assertEquals(doorDTO.getId(), mockExteriorDoor.getId());
        Assertions.assertEquals(doorDTO.isOpen(), mockExteriorDoor.isOpen());
        Assertions.assertEquals(doorDTO.isLocked(), mockExteriorDoor.isLocked());
    }

    @Test
    public void unlockDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(false, true);
        final DoorDTO doorDTO = setUpExteriorDoor(mockExteriorDoor, false, false, false);

        Assertions.assertEquals(doorDTO.getId(), mockExteriorDoor.getId());
        Assertions.assertEquals(doorDTO.isOpen(), mockExteriorDoor.isOpen());
        Assertions.assertEquals(doorDTO.isLocked(), mockExteriorDoor.isLocked());
    }

    @Test
    public void lockDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(false, false);
        final DoorDTO doorDTO = setUpExteriorDoor(mockExteriorDoor, false, false, true);

        Assertions.assertEquals(doorDTO.getId(), mockExteriorDoor.getId());
        Assertions.assertEquals(doorDTO.isOpen(), mockExteriorDoor.isOpen());
        Assertions.assertEquals(doorDTO.isLocked(), mockExteriorDoor.isLocked());
    }


    @Test
    public void openWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(false, false);
        final WindowDTO windowDTO = setUpWindow(mockHouseWindow, true, true, false);

        Assertions.assertEquals(windowDTO.getId(), mockHouseWindow.getId());
        Assertions.assertEquals(windowDTO.isOpen(), mockHouseWindow.isOpen());
        Assertions.assertEquals(windowDTO.isBlocked(), mockHouseWindow.isBlocked());
    }

    @Test
    public void closeWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(true, false);
        final WindowDTO windowDTO = setUpWindow(mockHouseWindow, true, false, false);

        Assertions.assertEquals(windowDTO.getId(), mockHouseWindow.getId());
        Assertions.assertEquals(windowDTO.isOpen(), mockHouseWindow.isOpen());
        Assertions.assertEquals(windowDTO.isBlocked(), mockHouseWindow.isBlocked());
    }

    @Test
    public void unblockWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(false, true);
        final WindowDTO windowDTO = setUpWindow(mockHouseWindow, false, false, false);

        Assertions.assertEquals(windowDTO.getId(), mockHouseWindow.getId());
        Assertions.assertEquals(windowDTO.isOpen(), mockHouseWindow.isOpen());
        Assertions.assertEquals(windowDTO.isBlocked(), mockHouseWindow.isBlocked());
    }

    @Test
    public void blockWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(false, false);
        final WindowDTO windowDTO = setUpWindow(mockHouseWindow, false, false, true);

        Assertions.assertEquals(windowDTO.getId(), mockHouseWindow.getId());
        Assertions.assertEquals(windowDTO.isOpen(), mockHouseWindow.isOpen());
        Assertions.assertEquals(windowDTO.isBlocked(), mockHouseWindow.isBlocked());
    }

    private LightDTO setUpLight(final Light mockLight, final boolean desiredState) {
        when(lightRepository.findById(MOCK_HOUSE_LIGHT_ID)).thenReturn(java.util.Optional.ofNullable(mockLight));
        when(mvcConversionService.convert(lightRepository.save(mockLight), LightDTO.class))
                .thenReturn(LightDTO.builder().id(MOCK_HOUSE_LIGHT_ID).isLightOn(desiredState).build());

        return houseService.modifyLightState(MOCK_HOUSE_LIGHT_ID, desiredState);
    }

    private DoorDTO setUpExteriorDoor(final ExteriorDoor exteriorDoor,
                                      final boolean doWeOpen,
                                      final boolean finalOpen,
                                      final boolean finalLocked) {
        when(exteriorDoorRepository.findById(MOCK_HOUSE_EXTERIOR_DOOR_ID)).thenReturn(java.util.Optional.ofNullable(exteriorDoor));

        when(mvcConversionService.convert(exteriorDoorRepository.save(exteriorDoor), DoorDTO.class))
                .thenReturn(DoorDTO.builder()
                        .id(MOCK_HOUSE_EXTERIOR_DOOR_ID)
                        .open(finalOpen)
                        .locked(finalLocked)
                        .build());

        return houseService.modifyExteriorDoorState(MOCK_HOUSE_EXTERIOR_DOOR_ID, doWeOpen, doWeOpen ? finalOpen : finalLocked);
    }

    private WindowDTO setUpWindow(final HouseWindow mockHouseWindow,
                                  final boolean doWeOpen,
                                  final boolean finalOpen,
                                  final boolean finalBlocked) {
        when(houseWindowRepository.findById(MOCK_HOUSE_WINDOW_ID)).thenReturn(java.util.Optional.ofNullable(mockHouseWindow));
        when(mvcConversionService.convert(houseWindowRepository.save(mockHouseWindow), WindowDTO.class))
                .thenReturn(WindowDTO.builder().id(MOCK_HOUSE_WINDOW_ID).open(finalOpen).blocked(finalBlocked).build());

        return houseService.modifyWindowState(MOCK_HOUSE_WINDOW_ID, doWeOpen, doWeOpen ? finalOpen : finalBlocked);
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
}
