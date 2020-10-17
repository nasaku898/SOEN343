package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.repository.ExteriorDoorRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dal.repository.InteriorDoorRepository;
import com.soen343.shs.dal.repository.LightRepository;
import com.soen343.shs.dal.service.exceptions.exteriorDoor.ExteriorDoorAlreadyLockedException;
import com.soen343.shs.dal.service.exceptions.exteriorDoor.ExteriorDoorAlreadyUnlockedException;
import com.soen343.shs.dal.service.exceptions.exteriorDoor.ExteriorDoorNotFoundException;
import com.soen343.shs.dal.service.exceptions.houseWindow.HouseWindowAlreadyClosedException;
import com.soen343.shs.dal.service.exceptions.houseWindow.HouseWindowAlreadyOpenedException;
import com.soen343.shs.dal.service.exceptions.houseWindow.HouseWindowBlockedException;
import com.soen343.shs.dal.service.exceptions.houseWindow.HouseWindowNotFoundException;
import com.soen343.shs.dal.service.exceptions.light.LightIsAlreadyOffException;
import com.soen343.shs.dal.service.exceptions.light.LightIsAlreadyOnException;
import com.soen343.shs.dal.service.exceptions.light.LightNotFoundException;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.LightDTO;
import com.soen343.shs.dto.WindowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final LightRepository lightRepository;
    private final HouseWindowRepository houseWindowRepository;
    private final InteriorDoorRepository interiorDoorRepository;
    private final ExteriorDoorRepository exteriorDoorRepository;
    private final ConversionService mvcConversionService;

    private static String getWindowNotFoundError(final long windowId) {
        return String.format("Error: Window with ID %d does not exist. Please enter a valid window ID.", windowId);
    }

    private static String getHouseNotFoundError(final long houseId) {
        return String.format("Error: House with ID %d does not exist. Please enter a valid house ID.", houseId);
    }

    private static String getExteriorDoorNotFoundError(final long exteriorDoorId) {
        return String.format("Error: Exterior door with ID %d does not exist. Please enter a valid exterior door ID.", exteriorDoorId);
    }

    private static String getLightNotFoundError(final long lightId) {
        return String.format("Error: Light with ID %d does not exist. Please enter a valid light ID.", lightId);
    }

    public WindowDTO openWindow(final long windowId) {
        // Get the window from the db
        final HouseWindow window = houseWindowRepository.findById(windowId).orElseThrow(() -> new HouseWindowNotFoundException(getWindowNotFoundError(windowId)));

        // Check if window is already opened
        if (window.isOpen()) {
            throw new HouseWindowAlreadyOpenedException(String.format("Error: Window with ID %d is already opened", windowId));
        }

        // Check if the window is blocked
        checkIfWindowIsBlocked(window);

        // Open the window
        window.setOpen(true);
        houseWindowRepository.save(window);

        // Return HouseWindowDTO to client
        return mvcConversionService.convert(window, WindowDTO.class);
    }

    public WindowDTO closeWindow(final long windowId) {
        // Get the window from the db
        final HouseWindow window = houseWindowRepository.findById(windowId).orElseThrow(() -> new HouseWindowNotFoundException(getWindowNotFoundError(windowId)));

        // Check if window is already opened
        if (!window.isOpen()) {
            throw new HouseWindowAlreadyClosedException(String.format("Error: Window with ID %d is already closed", windowId));
        }

        // Check if the window is blocked
        checkIfWindowIsBlocked(window);

        // Close the window
        window.setOpen(false);

        // Return HouseWindowDTO to client
        return mvcConversionService.convert(houseWindowRepository.save(window), WindowDTO.class);
    }

    private static void checkIfWindowIsBlocked(final HouseWindow window) {
        final String WINDOW_BLOCKED_ERROR = "Error: Window with ID %d cannot be opened since it is blocked.";
        if (window.isBlocked()) {
            throw new HouseWindowBlockedException(String.format(WINDOW_BLOCKED_ERROR, window.getId()));
        }
    }

    public DoorDTO unlockExteriorDoor(final long exteriorDoorId) {
        // Get the door from the db
        final ExteriorDoor exteriorDoor = exteriorDoorRepository.findById(exteriorDoorId).orElseThrow(() -> new ExteriorDoorNotFoundException(getExteriorDoorNotFoundError(exteriorDoorId)));

        // Check if door is already unlocked
        if (!exteriorDoor.isLocked()) {
            throw new ExteriorDoorAlreadyUnlockedException(String.format("Error: Exterior door with ID %d is already unlocked.", exteriorDoorId));
        }

        // Unlock door
        exteriorDoor.setLocked(false);

        // Return DoorDTO to client
        return mvcConversionService.convert(exteriorDoorRepository.save(exteriorDoor), DoorDTO.class);
    }

    public DoorDTO lockExteriorDoor(final long exteriorDoorId) {
        // Get the door from the db
        final ExteriorDoor exteriorDoor = exteriorDoorRepository.findById(exteriorDoorId).orElseThrow(() -> new ExteriorDoorNotFoundException(getExteriorDoorNotFoundError(exteriorDoorId)));

        // Check if door is already locked
        if (exteriorDoor.isLocked()) {
            throw new ExteriorDoorAlreadyLockedException(String.format("Error: Exterior door with ID %d is already locked.", exteriorDoorId));
        }

        // Lock door
        exteriorDoor.setLocked(true);

        // Return DoorDTO to client
        return mvcConversionService.convert(exteriorDoorRepository.save(exteriorDoor), DoorDTO.class);
    }

    public LightDTO turnOnLight(final long lightId) {
        // Get the light from the db
        final Light light = lightRepository.findById(lightId).orElseThrow(() -> new LightNotFoundException(getLightNotFoundError(lightId)));

        // Check if light is already on
        if (light.isLightOn()) {
            throw new LightIsAlreadyOnException(String.format("Error: Light with ID %d is already on.", lightId));
        }

        // Turn on light
        light.setLightOn(true);

        // Return LightDTO to client
        return mvcConversionService.convert(lightRepository.save(light), LightDTO.class);
    }

    public LightDTO turnOffLight(final long lightId) {
        // Get the light from the db
        final Light light = lightRepository.findById(lightId).orElseThrow(() -> new LightNotFoundException(getLightNotFoundError(lightId)));

        // Check if light is already off
        if (!light.isLightOn()) {
            throw new LightIsAlreadyOffException(String.format("Error: Light with ID %d is already off.", lightId));
        }

        // Turn off light
        light.setLightOn(false);

        // Return LightDTO to client
        return mvcConversionService.convert(lightRepository.save(light), LightDTO.class);
    }
}
