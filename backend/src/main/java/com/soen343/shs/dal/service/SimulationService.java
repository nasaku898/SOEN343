package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dto.HouseDTO;
import com.soen343.shs.dto.RoomDTO;
import com.soen343.shs.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SimulationService {

    private final HouseService houseService;
    private final ConversionService mvcConversionService;
    private final RoomService roomService;
    private final UserService userService;

    /**
     * @param houseId     a house id
     * @param temperature new outside temperature
     * @return HouseDTO object reflecting the changes made to the object
     */
    public HouseDTO setTemperatureOutside(final long houseId, final double temperature) {
        final House house = houseService.fetchHouse(houseId);
        house.getCity().setTemperatureOutside(temperature);
        return mvcConversionService.convert(houseService.fetchHouse(houseId), HouseDTO.class);
    }

    /**
     * @param houseId a house id
     * @return outside temperature
     */
    public double getTemperatureOutside(final long houseId) {
        return houseService.getHouse(houseId).getCity().getTemperatureOutside();
    }

    /**
     * @param username username of user
     * @param roomId   a room id to transfer to
     * @return UserDTO object reflecting the changes made to the object
     */

    public <DTO extends UserDTO> UserDTO moveUserToRoom(final String username,
                                                        final long roomId,
                                                        final Class<DTO> dto) {
        final UserDTO user = userService.getUserByUsername(username, dto);
        if (user.isOutside()) {
            user.setOutside(false);
        }
        user.getRoomId().put(roomId, roomService.getRoom(roomId).getName());
        userService.updateUser(user.getId(), user);

        return user;
    }

    /**
     * @param houseId a house id
     * @return list of RoomDTO
     */
    public Set<RoomDTO> findAllRooms(final Long houseId) {
        return mvcConversionService.convert(houseService.fetchHouse(houseId), HouseDTO.class).getRooms();
    }

    public Set<Room> fetchRoomsState(final long houseId) {
        return houseService.fetchHouse(houseId).getRooms();
    }

    private static SHSNotFoundException getShsNotFoundException(final long houseId) {
        return new SHSNotFoundException(String.format("House with id: %d doesn't exist", houseId));
    }
}
