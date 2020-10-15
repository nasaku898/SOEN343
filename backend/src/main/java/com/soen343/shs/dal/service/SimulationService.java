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
                             final HouseWindowRepository houseWindowRepository,
                             final ConversionService mvcConversionService) {
        this.houseMemberRepository = houseMemberRepository;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.houseWindowRepository = houseWindowRepository;
        this.mvcConversionService = mvcConversionService;
    }

    public House findHouse(final long houseId) {
        return Optional.of(houseRepository.findById(houseId).get()).orElseThrow(HouseNotFoundException::new);
    }

    public void moveUserToRoom(final String name, final long roomId) {
        final Room room = findRoom(roomId);

        final HouseMember houseMember = houseMemberRepository.findByName(name);
        houseMember.setLocation(room);

        houseMemberRepository.save(houseMember);
    }

    public void addObjectToWindow(final long windowId) {
        final Optional<HouseWindow> houseWindowOptional = houseWindowRepository.findById(windowId);

        if (!houseWindowOptional.isPresent()) {
            throw new HouseWindowNotFoundException();
        }

        final HouseWindow houseWindow = houseWindowOptional.get();
        houseWindow.setBlocked(true);
        houseWindowRepository.save(houseWindow);
    }

    public HouseMemberDTO createNewHouseMember(final HouseMemberDTO houseMemberDTO) {
        final Room room = findRoom(houseMemberDTO.getRoomId());

        final HouseMember houseMember = new HouseMember();

        houseMember.setName(houseMemberDTO.getName());
        houseMember.setLocation(room);
        houseMemberRepository.save(houseMember);

        return mvcConversionService.convert(houseMember, HouseMemberDTO.class);
    }

    private Room findRoom(final long roomId) {
        return Optional.of(roomRepository.findById(roomId).get()).orElseThrow(RoomNotFoundException::new);
    }

    public void loadHouse(final LoadHouseDTO loadHouseDTO) {
        final Set<LoadRoomDTO> rooms = loadHouseDTO.getRooms();
        final Set<Room> roomsToAdd = new HashSet<Room>();
        final House house = new House();

        for (LoadRoomDTO room : rooms) {
            final Room roomToAdd = new Room();

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
        final Set<Door> doorsToAdd = new HashSet<Door>();

        for (LoadDoorDTO door : doors) {
            final Door doorToAdd = mvcConversionService.convert(door, Door.class);
            doorsToAdd.add(doorToAdd);
        }

        return doorsToAdd;
    }

    private Set<Light> loadLights(final Set<LoadLightDTO> lights) {
        final Set<Light> lightsToAdd = new HashSet<Light>();

        for (LoadLightDTO light : lights) {
            final Light lightToAdd = mvcConversionService.convert(light, Light.class);
            lightsToAdd.add(lightToAdd);
        }

        return lightsToAdd;
    }

    private Set<HouseWindow> loadHouseWindow(final Set<LoadHouseWindowDTO> windows) {
        final Set<HouseWindow> houseWindowsToAdd = new HashSet<HouseWindow>();

        for (LoadHouseWindowDTO houseWindow : windows) {
            final HouseWindow houseWindowToAdd = mvcConversionService.convert(houseWindow, HouseWindow.class);
            houseWindowsToAdd.add(houseWindowToAdd);
        }

        return houseWindowsToAdd;
    }
}