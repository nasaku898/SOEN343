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
        setUpLightAndAssertStateChange(false, true);
    }

    @Test
    public void turnLightOff() {
        setUpLightAndAssertStateChange(true, false);
    }

    @Test
    public void openDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(false, false);
        setUpExteriorDoorAndAssertStateChange(mockExteriorDoor, true, true, false);
    }

    @Test
    public void closeDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(true, false);
        setUpExteriorDoorAndAssertStateChange(mockExteriorDoor, true, false, false);

    }

    @Test
    public void unlockDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(false, true);
        setUpExteriorDoorAndAssertStateChange(mockExteriorDoor, false, false, false);
    }

    @Test
    public void lockDoor() {
        final ExteriorDoor mockExteriorDoor = buildMockExteriorDoor(false, false);
        setUpExteriorDoorAndAssertStateChange(mockExteriorDoor, false, false, true);
    }


    @Test
    public void openWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(false, false);
        setUpWindowAndAssertStateChange(mockHouseWindow, true, true, false);
    }

    @Test
    public void closeWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(true, false);
        setUpWindowAndAssertStateChange(mockHouseWindow, true, false, false);

    }

    @Test
    public void unblockWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(false, true);
        setUpWindowAndAssertStateChange(mockHouseWindow, false, false, false);
    }

    @Test
    public void blockWindow() {
        final HouseWindow mockHouseWindow = buildMockHouseWindow(false, false);
        setUpWindowAndAssertStateChange(mockHouseWindow, false, false, true);
    }

    private void setUpLightAndAssertStateChange(final boolean initialState, final boolean desiredState) {
        final Light mockLight = buildMockLight(initialState);
        when(lightRepository.findById(MOCK_HOUSE_LIGHT_ID)).thenReturn(java.util.Optional.ofNullable(mockLight));
        when(mvcConversionService.convert(lightRepository.save(mockLight), LightDTO.class))
                .thenReturn(LightDTO.builder()
                        .id(MOCK_HOUSE_LIGHT_ID)
                        .isLightOn(desiredState)
                        .build());

        final LightDTO dto = houseService.modifyLightState(MOCK_HOUSE_LIGHT_ID, desiredState);

        Assertions.assertEquals(dto.getId(), mockLight.getId());
        Assertions.assertEquals(dto.isLightOn(), mockLight.getIsLightOn());
    }


    private void setUpExteriorDoorAndAssertStateChange(final ExteriorDoor exteriorDoor,
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

        final DoorDTO dto = houseService.modifyExteriorDoorState(MOCK_HOUSE_EXTERIOR_DOOR_ID, doWeOpen, doWeOpen ? finalOpen : finalLocked);

        Assertions.assertEquals(dto.getId(), exteriorDoor.getId());
        Assertions.assertEquals(dto.getOpen(), exteriorDoor.getOpen());
        Assertions.assertEquals(dto.getLocked(), exteriorDoor.getLocked());
    }

    private void setUpWindowAndAssertStateChange(final HouseWindow mockHouseWindow,
                                                 final boolean doWeOpen,
                                                 final boolean finalOpen,
                                                 final boolean finalBlocked) {
        when(houseWindowRepository.findById(MOCK_HOUSE_WINDOW_ID)).thenReturn(java.util.Optional.ofNullable(mockHouseWindow));
        when(mvcConversionService.convert(houseWindowRepository.save(mockHouseWindow), WindowDTO.class))
                .thenReturn(WindowDTO.builder()
                        .id(MOCK_HOUSE_WINDOW_ID)
                        .open(finalOpen)
                        .blocked(finalBlocked)
                        .build());

        final WindowDTO dto = houseService.modifyWindowState(MOCK_HOUSE_WINDOW_ID, doWeOpen, doWeOpen ? finalOpen : finalBlocked);
        Assertions.assertEquals(dto.getId(), mockHouseWindow.getId());
        Assertions.assertEquals(dto.isOpen(), mockHouseWindow.getOpen());
        Assertions.assertEquals(dto.isBlocked(), mockHouseWindow.getBlocked());
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
