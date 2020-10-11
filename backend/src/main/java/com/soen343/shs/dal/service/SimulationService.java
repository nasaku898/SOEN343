package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.*;
import com.soen343.shs.dal.repository.HouseMemberRepository;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException;
import com.soen343.shs.dal.service.exceptions.houseWindow.HouseWindowNotFoundException;
import com.soen343.shs.dal.service.exceptions.room.RoomNotFoundException;
import com.soen343.shs.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SimulationService {

    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;
    private final HouseMemberRepository houseMemberRepository;
    private final HouseWindowRepository houseWindowRepository;
    private final ConversionService mvcConversionService;

    @Autowired
    public SimulationService(final HouseRepository houseRepository,
                             final HouseMemberRepository houseMemberRepository,
                             final RoomRepository roomRepository,
                             final HouseWindowRepository houseWindowRepository, final ConversionService mvcConversionService) {
        this.houseMemberRepository = houseMemberRepository;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.houseWindowRepository = houseWindowRepository;
        this.mvcConversionService = mvcConversionService;
    }

    public House findHouse(final long houseId) {
        Optional<House> houseOptional = houseRepository.findById(houseId);

        if (!houseOptional.isPresent()) {
            throw new HouseNotFoundException();
        }

        House house = houseOptional.get();

        return house;
    }

    public void moveUserToRoom(final String name, final long roomId) {
        Room room = findRoom(roomId);

        HouseMember houseMember = houseMemberRepository.findByName(name);
        houseMember.setLocation(room);

        houseMemberRepository.save(houseMember);
    }

    public void addObjectToWindow(final long windowId) {
        Optional<HouseWindow> houseWindowOptional = houseWindowRepository.findById(windowId);

        if (!houseWindowOptional.isPresent()) {
            throw new HouseWindowNotFoundException();
        }

        HouseWindow houseWindow = houseWindowOptional.get();
        houseWindow.setBlocked(true);
        houseWindowRepository.save(houseWindow);
    }

    public void createNewHouseMember(final HouseMemberDTO houseMemberDTO) {
        Room room = findRoom(houseMemberDTO.getRoomId());

        HouseMember houseMember = new HouseMember();

        houseMember.setName(houseMemberDTO.getName());
        houseMember.setLocation(room);
        houseMemberRepository.save(houseMember);
    }

    private Room findRoom(final long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (!roomOptional.isPresent()) {
            throw new RoomNotFoundException();
        }

        return roomOptional.get();
    }

    public void loadHouse(final LoadHouseDTO loadHouseDTO) {
        Set<LoadRoomDTO> rooms = loadHouseDTO.getRooms();
        Set<Room> roomsToAdd = new HashSet<Room>();
        House house = new House();

        for (LoadRoomDTO room : rooms) {
            Room roomToAdd = new Room();

            Set<LoadDoorDTO> doors = room.getDoors();
            Set<LoadLightDTO> lights = room.getLights();
            Set<LoadHouseWindowDTO> windows = room.getHouseWindows();

            roomToAdd.setDoors(loadDoors(doors));
            roomToAdd.setLights(loadLights(lights));
            roomToAdd.setHouseWindows(loadHouseWindow(windows));

            roomToAdd.setName(room.getName());
            roomsToAdd.add(roomToAdd);
        }

        house.setRooms(roomsToAdd);
        houseRepository.save(house);
    }

    private Set<Door> loadDoors(final Set<LoadDoorDTO> doors) {
        Set<Door> doorsToAdd = new HashSet<Door>();

        for (LoadDoorDTO door : doors) {
            Door doorToAdd = new Door();
            doorToAdd.setOpen(door.isOpen());
            doorToAdd.setRooms(door.getRooms());
            doorsToAdd.add(doorToAdd);
        }

        return doorsToAdd;
    }

    private Set<Light> loadLights(final Set<LoadLightDTO> lights) {
        Set<Light> lightsToAdd = new HashSet<Light>();

        for (LoadLightDTO light : lights) {
            Light lightToAdd = new Light();
            lightToAdd.setLightOn(light.isLightOn());
            lightsToAdd.add(lightToAdd);
        }

        return lightsToAdd;
    }

    private Set<HouseWindow> loadHouseWindow(final Set<LoadHouseWindowDTO> windows) {
        Set<HouseWindow> houseWindowsToAdd = new HashSet<HouseWindow>();

        for (LoadHouseWindowDTO houseWindow : windows) {
            HouseWindow houseWindowToAdd = new HouseWindow();
            houseWindowToAdd.setOpen(houseWindow.isOpen());
            houseWindowToAdd.setBlocked(houseWindow.isBlocked());
            houseWindowsToAdd.add(houseWindowToAdd);
        }

        return houseWindowsToAdd;
    }
}
