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
import com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException;
import com.soen343.shs.dal.service.exceptions.houseWindow.*;
import com.soen343.shs.dal.service.exceptions.light.LightIsAlreadyOffException;
import com.soen343.shs.dal.service.exceptions.light.LightIsAlreadyOnException;
import com.soen343.shs.dal.service.exceptions.light.LightNotFoundException;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.LightDTO;
import com.soen343.shs.dto.WindowDTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class HouseService {
    private final LightRepository lightRepository;
    private final HouseWindowRepository houseWindowRepository;
    private final InteriorDoorRepository interiorDoorRepository;
    private final ExteriorDoorRepository exteriorDoorRepository;
    private final ConversionService mvcConversionService;

    public HouseService(final LightRepository lightRepository,
                        final HouseWindowRepository houseWindowRepository,
                        final InteriorDoorRepository interiorDoorRepository,
                        final ExteriorDoorRepository exteriorDoorRepository,
                        final ConversionService mvcConversionService) {
        this.lightRepository = lightRepository;
        this.houseWindowRepository = houseWindowRepository;
        this.interiorDoorRepository = interiorDoorRepository;
        this.exteriorDoorRepository = exteriorDoorRepository;
        this.mvcConversionService = mvcConversionService;
    }

    private String getWindowNotFoundError(long windowId) {
        return "Error: Window with ID " + windowId + " does not exist. Please enter a valid window ID.";
    }

    private String getHouseNotFoundError(long houseId) {
        return "Error: House with ID " + houseId + " does not exist. Please enter a valid house ID.";
    }

    private String getExteriorDoorNotFoundError(long exteriorDoorId) {
        return "Error: Exterior door with ID " + exteriorDoorId + " does not exist. Please enter a valid exterior door ID.";
    }

    private String getLightNotFoundError(long lightId) {
        return "Error: Light with ID " + lightId + " does not exist. Please enter a valid light ID.";
    }

    public WindowDTO openWindow(long windowId) {
        // Get the window from the db
        final HouseWindow window = houseWindowRepository.findById(windowId).orElseThrow(() -> new HouseNotFoundException(getHouseNotFoundError(windowId)));

        // Check if window is already opened
        if (window.isOpen()) {
            throw new HouseWindowAlreadyOpenedException("Error: Window with ID " + windowId + " is already opened");
        }

        // Check if the window is blocked
        if (window.isBlocked()) {
            throw new HouseWindowBlockedException("Error: Window with ID " + windowId + " cannot be opened since it is blocked.");
        }

        // Open the window
        window.setOpen(true);
        houseWindowRepository.save(window);

        // Return HouseWindowDTO to client
        return mvcConversionService.convert(window, WindowDTO.class);
    }

    public WindowDTO closeWindow(long windowId) {
        // Get the window from the db
        final HouseWindow window = houseWindowRepository.findById(windowId).orElseThrow(() -> new HouseNotFoundException(getHouseNotFoundError(windowId)));

        // Check if window is already opened
        if (!window.isOpen()) {
            throw new HouseWindowAlreadyClosedException("Error: Window with ID " + windowId + " is already closed");
        }

        // Check if the window is blocked
        if (window.isBlocked()) {
            throw new HouseWindowBlockedException("Error: Window with ID " + windowId + " cannot be closed since it is blocked.");
        }

        // Close the window
        window.setOpen(false);
        houseWindowRepository.save(window);

        // Return HouseWindowDTO to client
        return mvcConversionService.convert(window, WindowDTO.class);
    }

    public DoorDTO unlockExteriorDoor(long exteriorDoorId) {
        // Get the door from the db
        final ExteriorDoor exteriorDoor = exteriorDoorRepository.findById(exteriorDoorId).orElseThrow(() -> new ExteriorDoorNotFoundException(getExteriorDoorNotFoundError(exteriorDoorId)));

        // Check if door is already unlocked
        if (!exteriorDoor.isLocked()) {
            throw new ExteriorDoorAlreadyUnlockedException("Error: Exterior door with ID " + exteriorDoorId + " is already unlocked.");
        }

        // Unlock door
        exteriorDoor.setLocked(false);
        exteriorDoorRepository.save(exteriorDoor);

        // Return DoorDTO to client
        return mvcConversionService.convert(exteriorDoor, DoorDTO.class);
    }

    public DoorDTO lockExteriorDoor(long exteriorDoorId) {
        // Get the door from the db
        final ExteriorDoor exteriorDoor = exteriorDoorRepository.findById(exteriorDoorId).orElseThrow(() -> new ExteriorDoorNotFoundException(getExteriorDoorNotFoundError(exteriorDoorId)));

        // Check if door is already locked
        if (exteriorDoor.isLocked()) {
            throw new ExteriorDoorAlreadyLockedException("Error: Exterior door with ID " + exteriorDoorId + " is already locked.");
        }

        // Lock door
        exteriorDoor.setLocked(true);
        exteriorDoorRepository.save(exteriorDoor);

        // Return DoorDTO to client
        return mvcConversionService.convert(exteriorDoor, DoorDTO.class);
    }

    public LightDTO turnOnLight(long lightId) {
        // Get the light from the db
        final Light light = lightRepository.findById(lightId).orElseThrow(() -> new LightNotFoundException(getLightNotFoundError(lightId)));

        // Check if light is already on
        if (light.isLightOn()) {
            throw new LightIsAlreadyOnException("Error: Light with ID " + lightId + " is already on.");
        }

        // Turn on light
        light.setLightOn(true);
        lightRepository.save(light);

        // Return LightDTO to client
        return mvcConversionService.convert(light, LightDTO.class);
    }

    public LightDTO turnOffLight(long lightId) {
        // Get the light from the db
        final Light light = lightRepository.findById(lightId).orElseThrow(() -> new LightNotFoundException(getLightNotFoundError(lightId)));

        // Check if light is already off
        if (!light.isLightOn()) {
            throw new LightIsAlreadyOffException("Error: Light with ID " + lightId + " is already off.");
        }

        // Turn off light
        light.setLightOn(false);
        lightRepository.save(light);

        // Return LightDTO to client
        return mvcConversionService.convert(light, LightDTO.class);
    }
}
