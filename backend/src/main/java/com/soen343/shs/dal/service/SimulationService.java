package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.HouseMemberRepository;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException;
import com.soen343.shs.dal.service.exceptions.houseWindow.HouseWindowNotFoundException;
import com.soen343.shs.dal.service.exceptions.room.RoomNotFoundException;
import com.soen343.shs.dto.HouseLayoutDTO;
import com.soen343.shs.dto.HouseMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SimulationService {

    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;
    private final HouseMemberRepository houseMemberRepository;
    private final HouseWindowRepository houseWindowRepository;
    private final ConversionService mvcConversionService;

    @Autowired
    public SimulationService(HouseRepository houseRepository,
                             HouseMemberRepository houseMemberRepository,
                             RoomRepository roomRepository,
                             HouseWindowRepository houseWindowRepository, final ConversionService mvcConversionService) {
        this.houseMemberRepository = houseMemberRepository;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.houseWindowRepository = houseWindowRepository;
        this.mvcConversionService = mvcConversionService;
    }

    public HouseLayoutDTO findHouseLayout(final long houseId) {
        Optional<House> houseOptional = houseRepository.findById(houseId);

        if (!houseOptional.isPresent()) {
            throw new HouseNotFoundException();
        }

        House house = houseOptional.get();

        return mvcConversionService.convert(house, HouseLayoutDTO.class);
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

    public void createNewHouseMember(final HouseMemberDTO houseMemberDTO ) {
        Room room = findRoom(houseMemberDTO.getRoomId());

        HouseMember houseMember = new HouseMember();

        houseMember.setName(houseMemberDTO.getName());
        houseMember.setLocation(room);
        houseMemberRepository.save(houseMember);
    }

    private Room findRoom(final long roomId){
        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (!roomOptional.isPresent()) {
            throw new RoomNotFoundException();
        }

        return roomOptional.get();
    }
}
