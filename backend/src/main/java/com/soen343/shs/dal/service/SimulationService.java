package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.model.User;
import com.soen343.shs.dal.repository.HouseMemberRepository;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dal.repository.UserRepository;
import com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException;
import com.soen343.shs.dal.service.exceptions.houseWindow.HouseWindowNotFoundException;
import com.soen343.shs.dto.HouseMemberDTO;
import com.soen343.shs.dto.RoomDTO;
import com.soen343.shs.dto.UserDTO;
import com.soen343.shs.dto.WindowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimulationService {

    private final HouseRepository houseRepository;
    private final HouseMemberRepository houseMemberRepository;
    private final HouseWindowRepository houseWindowRepository;
    private final ConversionService mvcConversionService;
    private final ModelFetchHandler modelFetchHandler;
    private final UserRepository userRepository;

    /**
     * @param houseId a house id
     * @return house object
     */
    public House findHouse(final long houseId) {
        return houseRepository.findById(houseId).orElseThrow(HouseNotFoundException::new);
    }

    /**
     * @param name   name of the house member
     * @param roomId a room id to transfer to
     * @return HouseMemberDTO object reflecting the changes made to the object
     */
    public HouseMemberDTO moveHouseMemberToRoom(final String name, final long roomId) {
        final Room room = modelFetchHandler.findRoom(roomId);
        final HouseMember houseMember = houseMemberRepository.findByName(name);
        houseMember.setLocation(room);
        room.getUserIds().add(houseMember.getId());

        return mvcConversionService.convert(houseMemberRepository.save(houseMember), HouseMemberDTO.class);
    }

    public UserDTO moveUserToRoom(final String username, final long roomId) {
        final Room room = modelFetchHandler.findRoom(roomId);
        final User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        user.setLocation(room);
        room.getUserIds().add(user.getId());

        return mvcConversionService.convert(userRepository.save(user), UserDTO.class);
    }

    /**
     * @param windowId a window id
     * @return WindowDTO object reflecting the changes made to the object
     */
    public WindowDTO addObjectToWindow(final long windowId) {
        final HouseWindow houseWindow = houseWindowRepository.findById(windowId).orElseThrow(HouseWindowNotFoundException::new);

        houseWindow.setBlocked(true);
        return mvcConversionService.convert(houseWindowRepository.save(houseWindow), WindowDTO.class);
    }

    /**
     * @param houseId a house id
     * @return list of RoomDTO
     */
    public List<RoomDTO> findAllRooms(final Long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(HouseNotFoundException::new)
                .getRooms()
                .stream()
                .map(room -> mvcConversionService.convert(room, RoomDTO.class))
                .collect(Collectors.toList());
    }

    public Set<Room> fetchRoomsState(final long houseId) {
        final House house = houseRepository.findById(houseId).orElseThrow(HouseNotFoundException::new);
        return house.getRooms();
    }
}
