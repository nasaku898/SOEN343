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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModelFetchHandler {
    private final HouseMemberRepository houseMemberRepository;
    private final RoomRepository roomRepository;
    private final HouseWindowRepository houseWindowRepository;

    /**
     * @param roomId a room id
     * @return A room object
     */
    public Room findRoom(final long roomId) {
        return roomRepository.findById(roomId).orElseThrow(RoomNotFoundException::new);
    }

    /**
     * @param houseMemberId house member id
     * @return a room House member
     */
    public HouseMember findHouseMember(final long houseMemberId) {
        return houseMemberRepository.findById(houseMemberId).orElseThrow(HouseMemberNotFoundException::new);
    }

    /**
     * @param houseWindowId house window id
     * @return a house window
     */
    public HouseWindow findHouseWindow(final long houseWindowId) {
        return houseWindowRepository.findById(houseWindowId).orElseThrow(HouseWindowNotFoundException::new);
    }
}
