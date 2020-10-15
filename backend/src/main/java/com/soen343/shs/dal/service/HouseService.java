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
import com.soen343.shs.dal.service.exceptions.houseWindow.*;
import com.soen343.shs.dal.service.exceptions.light.LightIsAlreadyOffException;
import com.soen343.shs.dal.service.exceptions.light.LightIsAlreadyOnException;
import com.soen343.shs.dal.service.exceptions.light.LightNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseService {
    private final LightRepository lightRepository;
    private final HouseWindowRepository houseWindowRepository;
    private final InteriorDoorRepository interiorDoorRepository;
    private final ExteriorDoorRepository exteriorDoorRepository;

    public HouseService(LightRepository lightRepository, HouseWindowRepository houseWindowRepository, InteriorDoorRepository interiorDoorRepository, ExteriorDoorRepository exteriorDoorRepository) {
        this.lightRepository = lightRepository;
        this.houseWindowRepository = houseWindowRepository;
        this.interiorDoorRepository = interiorDoorRepository;
        this.exteriorDoorRepository = exteriorDoorRepository;
    }

    public void openWindow(long windowId) {
        // Get the window from the db
        Optional<HouseWindow> optionalWindow = houseWindowRepository.findById(windowId);

        // Check if window exists
        if (!optionalWindow.isPresent()) {
            throw new HouseWindowNotFoundException("Error: Window with ID " + windowId + " does not exist. Please enter a valid window ID.");
        }

        HouseWindow window = optionalWindow.get();

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
    }

    public void closeWindow(long windowId) {
        // Get the window from the db
        Optional<HouseWindow> optionalWindow = houseWindowRepository.findById(windowId);

        // Check if window exists
        if (!optionalWindow.isPresent()) {
            throw new HouseWindowNotFoundException("Error: Window with ID " + windowId + " does not exist. Please enter a valid window ID.");
        }

        HouseWindow window = optionalWindow.get();

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
    }

    public void unlockExteriorDoor(long exteriorDoorId) {
        // Get the door from the db
        Optional<ExteriorDoor>  optionalExteriorDoor = exteriorDoorRepository.findById(exteriorDoorId);

        // Check if door exists
        if (!optionalExteriorDoor.isPresent()) {
            throw new ExteriorDoorNotFoundException("Error: Exterior door with ID " + exteriorDoorId + " not found. Please enter a valid exterior door ID.");
        }

        ExteriorDoor exteriorDoor = optionalExteriorDoor.get();

        // Check if door is already unlocked
        if (!exteriorDoor.isLocked()) {
            throw new ExteriorDoorAlreadyUnlockedException("Error: Exterior door with ID " + exteriorDoorId + " is already unlocked.");
        }

        // Unlock door
        exteriorDoor.setLocked(false);
        exteriorDoorRepository.save(exteriorDoor);
    }

    public void lockExteriorDoor(long exteriorDoorId) {
        // Get the door from the db
        Optional<ExteriorDoor>  optionalExteriorDoor = exteriorDoorRepository.findById(exteriorDoorId);

        // Check if door exists
        if (!optionalExteriorDoor.isPresent()) {
            throw new ExteriorDoorNotFoundException("Error: Exterior door with ID " + exteriorDoorId + " not found. Please enter a valid exterior door ID.");
        }

        ExteriorDoor exteriorDoor = optionalExteriorDoor.get();

        // Check if door is already locked
        if (exteriorDoor.isLocked()) {
            throw new ExteriorDoorAlreadyLockedException("Error: Exterior door with ID " + exteriorDoorId + " is already locked.");
        }

        // Lock door
        exteriorDoor.setLocked(true);
        exteriorDoorRepository.save(exteriorDoor);
    }

    public void turnOnLight(long lightId) {
        // Get the light from the db
        Optional<Light> optionalLight = lightRepository.findById(lightId);

        // Check if light exists
        if (!optionalLight.isPresent()) {
            throw new LightNotFoundException("Error: Light with ID " + lightId + " does not exist. Please enter a valid Light ID.");
        }

        Light light = optionalLight.get();

        // Check if light is already on
        if (light.isLightOn()) {
            throw new LightIsAlreadyOnException("Error: Light with ID " + lightId + " is already on.");
        }

        // Turn on light
        light.setLightOn(true);
        lightRepository.save(light);
    }

    public void turnOffLight(long lightId) {
        // Get the light from the db
        Optional<Light> optionalLight = lightRepository.findById(lightId);

        // Check if light exists
        if (!optionalLight.isPresent()) {
            throw new LightNotFoundException("Error: Light with ID " + lightId + " does not exist. Please enter a valid Light ID.");
        }

        Light light = optionalLight.get();

        // Check if light is already off
        if (!light.isLightOn()) {
            throw new LightIsAlreadyOffException("Error: Light with ID " + lightId + " is already off.");
        }

        // Turn off light
        light.setLightOn(false);
        lightRepository.save(light);
    }
}
