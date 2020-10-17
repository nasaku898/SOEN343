package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.HouseMemberRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.service.exceptions.houseWindow.HouseWindowNotFoundException;
import com.soen343.shs.dal.service.exceptions.room.RoomNotFoundException;
import com.soen343.shs.dal.service.exceptions.user.HouseMemberNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ModelFetchHandler {
    private final HouseMemberRepository houseMemberRepository;
    private final RoomRepository roomRepository;
    private final HouseWindowRepository houseWindowRepository;

    public ModelFetchHandler(final HouseMemberRepository houseMemberRepository, final RoomRepository roomRepository, HouseWindowRepository houseWindowRepository) {
        this.houseMemberRepository = houseMemberRepository;
        this.roomRepository = roomRepository;
        this.houseWindowRepository = houseWindowRepository;
    }

    public Room findRoom(final long roomId) {
        return Optional.of(roomRepository.findById(roomId).get()).orElseThrow(RoomNotFoundException::new);
    }

    public HouseMember findHouseMember(final long houseMemberId) {
        return Optional.of(houseMemberRepository.findById(houseMemberId).get()).orElseThrow(HouseMemberNotFoundException::new);
    }

    public HouseWindow findHouseWindow(final long houseWindowId) {
        return Optional.of(houseWindowRepository.findById(houseWindowId).get()).orElseThrow(HouseWindowNotFoundException::new);
    }
}
